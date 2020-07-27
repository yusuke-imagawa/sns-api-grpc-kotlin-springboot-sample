package com.talking.api.grpc

import com.google.protobuf.Empty
import com.talking.api.grpc.interceptor.AuthInterceptor
import com.talking.constant.GrpcConstant
import com.talking.data.entity.PushTokenIos
import com.talking.data.service.PushTokenIosService
import com.talking.data.service.UserService
import com.talking.grpc.push_notify.*
import io.grpc.Status
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService
import org.springframework.beans.factory.annotation.Autowired

@GRpcService(interceptors = [AuthInterceptor::class])
class PushNotifyGrpcService: PushNotifyServiceGrpc.PushNotifyServiceImplBase() {

    @Autowired
    private lateinit var pushTokenIosService: PushTokenIosService

    @Autowired
    private lateinit var userService: UserService

    override fun savePushTokenIos(
        request: SavePushTokenIosRequest?,
        responseObserver: StreamObserver<Empty>?) {

        if (request == null || responseObserver == null) {
            return
        }

        val authUser = GrpcConstant.AUTH_USER_CONTEXT_KEY.get()

        val sameTokens: List<PushTokenIos> =
            pushTokenIosService.findByUserIdAndDeviceToken(authUser.id, request.deviceToken)
        if (sameTokens.isNotEmpty()) {
            // 既に登録済みのデバイストークンである場合
            responseObserver.onError(Status.ALREADY_EXISTS.asRuntimeException())
            return
        }

        val sameUserTokens: List<PushTokenIos>
            = pushTokenIosService.findByUserId(authUser.id)

        if (sameUserTokens.isNotEmpty()) {
            // すでに登録済みのレコードのtokenを更新
            for (sameUserToken in sameUserTokens) {
                sameUserToken.deviceToken = request.deviceToken
                pushTokenIosService.save(sameUserToken)
            }
        } else {
            // 新規登録
            val newPushTokenIos = PushTokenIos(
                user = authUser,
                deviceToken = request.deviceToken
            )
            pushTokenIosService.save(newPushTokenIos)
        }

        val response = Empty.newBuilder().build()
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }

    override fun updateNotify(request: UpdateNotifyRequest?, responseObserver: StreamObserver<Empty>?) {
        if (request == null || responseObserver == null) {
            return
        }

        val authUser = GrpcConstant.AUTH_USER_CONTEXT_KEY.get()
        authUser.isEnabledNotify = request.isEnabled
        userService.save(authUser)

        val response = Empty.newBuilder().build()
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}