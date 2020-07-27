package com.talking.api.grpc

import com.google.protobuf.Empty
import com.talking.api.grpc.interceptor.AuthInterceptor
import com.talking.application.pushnotify.PushNotifyService
import com.talking.constant.GrpcConstant
import com.talking.data.entity.CallingLog
import com.talking.data.entity.CallingStatus
import com.talking.data.service.CallingLogService
import com.talking.data.service.UserService
import com.talking.grpc.calling.*
import io.grpc.Status
import io.grpc.stub.ServerCallStreamObserver
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

@GRpcService(interceptors = [AuthInterceptor::class])
class CallingGrpcService: CallingServiceGrpc.CallingServiceImplBase() {

    @Autowired
    private lateinit var callingLogService: CallingLogService

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var pushNotifyService: PushNotifyService

    val receiveCallingStatusObserverMap: MutableMap<Long, StreamObserver<StatusResponse>> = mutableMapOf()

    override fun startCalling(request: StartCallingRequest?, responseObserver: StreamObserver<StartCallingResponse>?) {

        if (request == null || responseObserver == null) {
            return
        }

        val fromUser = GrpcConstant.AUTH_USER_CONTEXT_KEY.get()

        // status = 開始中, 通話中 のレコードを検索。
            // from_id, to_idの両方で検索する。
        val fromUserCallingLogs: List<CallingLog> = callingLogService.findByUserIdAndStatus(
            fromUser.id, listOf(CallingStatus.STARTING, CallingStatus.TALKING))

        if (fromUserCallingLogs.isNotEmpty()) {
            // 開始中、通話中のレコードがある場合、終了に更新する。
            for (fromUserCallingLog in fromUserCallingLogs) {
                fromUserCallingLog.status = CallingStatus.END
                callingLogService.save(fromUserCallingLog)
            }
        }

        // 相手のユーザーの終了していない通話ログを取得
        val notEndCallingLogs: List<CallingLog> = callingLogService.findStartingOrTalkingLogs(
            userId = request.toUserId,
            inTalkingContinueMinute = 3) // continue APIが3分以内に呼び出されていること
        if (notEndCallingLogs.isNotEmpty()) {
            // 相手が通話中のレスポンスを返す。
            val response = StartCallingResponse.newBuilder()
                .setResultType(StartCallingResultType.TO_USER_IN_TALKING)
                .build()
            responseObserver.onNext(response)
            responseObserver.onCompleted()
            return
        }

        // calling_logsテーブルのレコード作成:
            // status = 開始中
        val toUser = userService.findById(request.toUserId)
        if (toUser == null) {
            responseObserver.onError(Status.INVALID_ARGUMENT.asRuntimeException())
            return
        }

        val callingLog = CallingLog(
            fromUser = fromUser,
            toUser = toUser,
            status = CallingStatus.STARTING,
            startDatetime = Date())
        callingLogService.save(callingLog)

        val response = StartCallingResponse.newBuilder()
            .setResultType(StartCallingResultType.SUCCESS)
            .build()
        responseObserver.onNext(response)
        responseObserver.onCompleted()

        // receiveCallingStatusメソッドのobserverにステータス変更を通知する。
        val fromUserStatusObserver = getReceiveCallingStatusObserver(fromUser.id)
        val toUserStatusObserver = getReceiveCallingStatusObserver(request.toUserId)

        val statusResponse = StatusResponse.newBuilder()
            .setFromUserId(fromUser.id)
            .setToUserId(request.toUserId)
            .setStatus(com.talking.grpc.calling.CallingStatus.STARTING)
            .build()

        fromUserStatusObserver?.onNext(statusResponse)
        toUserStatusObserver?.onNext(statusResponse)

        // プッシュ通知
        if (toUser.isEnabledNotify) {
            val pushMessage: String = fromUser.name + " : 通話の着信がありました。"
            pushNotifyService.send(request.toUserId, pushMessage)
        }
    }

    override fun receiveCalling(
        request: ReceiveCallingRequest?,
        responseObserver: StreamObserver<Empty>?) {

        if (request == null || responseObserver == null) {
            return
        }

        val fromUser = GrpcConstant.AUTH_USER_CONTEXT_KEY.get()
        if (fromUser.id != request.toUserId) {
            responseObserver.onError(Status.INVALID_ARGUMENT.asRuntimeException())
            return
        }

        val toUser = userService.findById(request.toUserId)
        if (toUser == null) {
            responseObserver.onError(Status.INVALID_ARGUMENT.asRuntimeException())
            return
        }

        // status == 開始中 のレコードを取得
        val callingLogs: List<CallingLog> = callingLogService.findByFromIdAndToIdAndStatus(
            request.fromUserId, request.toUserId, listOf(CallingStatus.STARTING))

        if (callingLogs.isEmpty()) {
            // 見つからない場合にエラーを返す。
            responseObserver.onError(Status.INVALID_ARGUMENT.asRuntimeException())
            return
        }

        val callingLog = callingLogs.first()

        // calling_logsテーブルのレコード更新
            // status = 開始中 → 通話中
        callingLog.status = CallingStatus.TALKING
        callingLogService.save(callingLog)

        val response = Empty.newBuilder().build()
        responseObserver.onNext(response)
        responseObserver.onCompleted()

        // receiveCallingStatusメソッドのobserverにステータス変更を通知する。
        val fromUserStatusObserver = getReceiveCallingStatusObserver(request.fromUserId)
        val toUserStatusObserver = getReceiveCallingStatusObserver(request.toUserId)

        val statusResponse = StatusResponse.newBuilder()
            .setFromUserId(request.fromUserId)
            .setToUserId(request.toUserId)
            .setStatus(com.talking.grpc.calling.CallingStatus.TALKING)
            .build()

        fromUserStatusObserver?.onNext(statusResponse)
        toUserStatusObserver?.onNext(statusResponse)
    }

    override fun endCalling(request: EndCallingRequest?, responseObserver: StreamObserver<Empty>?) {

        // 電話終了
        if (request == null || responseObserver == null) {
            return
        }

        val authUser = GrpcConstant.AUTH_USER_CONTEXT_KEY.get()
        if (authUser.id != request.toUserId && authUser.id != request.fromUserId) {
            responseObserver.onError(Status.INVALID_ARGUMENT.asRuntimeException())
            return
        }

        // status == 通話中 のレコードを取得
        val callingLogs: List<CallingLog> = callingLogService.findByFromIdAndToIdAndStatus(
            request.fromUserId, request.toUserId, listOf(CallingStatus.TALKING))

        if (callingLogs.isEmpty()) {
            // 見つからない場合にエラーを返す。
            responseObserver.onError(Status.INVALID_ARGUMENT.asRuntimeException())
            return
        }

        val callingLog = callingLogs.first()

        // calling_logsテーブルのレコード更新
            // status = 通話終了
        callingLog.status = CallingStatus.END
        callingLog.endDatetime = Date()
        callingLogService.save(callingLog)

        val response = Empty.newBuilder().build()
        responseObserver.onNext(response)
        responseObserver.onCompleted()

        // receiveCallingStatusメソッドのobserverにステータス変更を通知する。
        val fromUserStatusObserver = getReceiveCallingStatusObserver(request.fromUserId)
        val toUserStatusObserver = getReceiveCallingStatusObserver(request.toUserId)

        val statusResponse = StatusResponse.newBuilder()
            .setFromUserId(request.fromUserId)
            .setToUserId(request.toUserId)
            .setStatus(com.talking.grpc.calling.CallingStatus.END)
            .build()

        fromUserStatusObserver?.onNext(statusResponse)
        toUserStatusObserver?.onNext(statusResponse)
    }

    override fun receiveCallingStatus(request: Empty?, responseObserver: StreamObserver<StatusResponse>?) {

        // 通話 / ステータス受信　ステータス: 開始、通話中、終了
        //    ・ステータスの変更時に受信する。
        //    ・clientからの接続時にも受信する。

        if (responseObserver == null) {
            return
        }

        val authUser = GrpcConstant.AUTH_USER_CONTEXT_KEY.get()

        if (receiveCallingStatusObserverMap.containsKey(authUser.id)) {
            receiveCallingStatusObserverMap.replace(authUser.id, responseObserver)
        } else {
            receiveCallingStatusObserverMap.put(authUser.id, responseObserver)
        }
    }

    override fun continueCalling(request: ContinueCallingRequest?, responseObserver: StreamObserver<Empty>?) {

        if (request == null || responseObserver == null) {
            return
        }

        val authUser = GrpcConstant.AUTH_USER_CONTEXT_KEY.get()
        if (authUser.id != request.toUserId && authUser.id != request.fromUserId) {
            responseObserver.onError(Status.INVALID_ARGUMENT.asRuntimeException())
            return
        }

        // status == 通話中 のレコードを取得
        val callingLogs: List<CallingLog> = callingLogService.findByFromIdAndToIdAndStatus(
            request.fromUserId, request.toUserId, listOf(CallingStatus.TALKING))

        if (callingLogs.isEmpty()) {
            // 見つからない場合にエラーを返す。
            responseObserver.onError(Status.INVALID_ARGUMENT.asRuntimeException())
            return
        }

        val callingLog = callingLogs.first()

        // calling_logsテーブルのレコード更新
        if (authUser.id == request.toUserId) {
            callingLog.lastToUserContinueDatetime = Date()
        } else if (authUser.id == request.fromUserId) {
            callingLog.lastFromUserContinueDatetime = Date()
        }
        callingLogService.save(callingLog)

        val response = Empty.newBuilder().build()
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }

    private fun getReceiveCallingStatusObserver(userId: Long) : StreamObserver<StatusResponse>? {

        val receiveCallingStatusObserver = receiveCallingStatusObserverMap[userId] ?: return null

        if (receiveCallingStatusObserver is ServerCallStreamObserver<StatusResponse> &&
                receiveCallingStatusObserver.isCancelled) {
            receiveCallingStatusObserverMap.remove(userId)
            return null
        }
        return receiveCallingStatusObserver
    }
}