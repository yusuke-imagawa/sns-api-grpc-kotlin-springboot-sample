package com.talking.api.grpc

import com.google.protobuf.Empty
import com.talking.api.grpc.converter.ChatConverter
import com.talking.api.grpc.converter.CommonConverter
import com.talking.api.grpc.converter.UserConverterService
import com.talking.api.grpc.interceptor.AuthInterceptor
import com.talking.application.pushnotify.PushNotifyService
import com.talking.constant.GrpcConstant
import com.talking.data.entity.User
import com.talking.data.service.BlockService
import com.talking.data.service.ChatMessageService
import com.talking.data.service.ProfileImageService
import com.talking.data.service.UserService
import com.talking.grpc.chat.*
import com.talking.grpc.chat.ChatServiceGrpc.ChatServiceImplBase
import io.grpc.Status
import io.grpc.stub.ServerCallStreamObserver
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService
import org.springframework.beans.factory.annotation.Autowired


@GRpcService(interceptors = [AuthInterceptor::class])
class ChatGrpcService: ChatServiceImplBase() {
    // この記事を参考に実装してみる。: gRPC Javaで双方向通信をやってみる
    // https://qiita.com/n_morioka/items/b97ec5140b7195fbbaa7#%E5%8F%8C%E6%96%B9%E5%90%91%E3%81%AErequest--response

    // もう一つ、チャットのコードのサンプル
    //　https://github.com/saturnism/grpc-by-example-java/tree/master/chat-example/chat-server/src/main

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var blockService: BlockService

    @Autowired
    private lateinit var chatMessageService: ChatMessageService

    @Autowired
    private lateinit var userConverterService: UserConverterService

    @Autowired
    private lateinit var profileImageService: ProfileImageService

    @Autowired
    private lateinit var pushNotifyService: PushNotifyService

    val receiveMessageObserverMap: MutableMap<Long, StreamObserver<ReceiveMessageResponse>> = mutableMapOf()

    override fun sendMessage(request: SendMessageRequest?, responseObserver: StreamObserver<Empty>?) {

        if (request == null || responseObserver == null) {
            return
        }

        val fromUser = GrpcConstant.AUTH_USER_CONTEXT_KEY.get()

        val toUser = userService.findById(request.toUserId)
        if (toUser == null) {
            responseObserver.onError(Status.INVALID_ARGUMENT.asRuntimeException())
            return
        }

        val messageEntity = com.talking.data.entity.ChatMessage(
            fromUser=fromUser,
            toUser = toUser,
            message = request.message)
        chatMessageService.save(messageEntity)

        val chatMessage = ChatConverter.toGrpcChatMessage(messageEntity)

        val fromUserGrpc = userConverterService.toGrpcUser(fromUser)
        val toUserGrpc = userConverterService.toGrpcUser(toUser)

        // 送信元、送信先のresponseObserverにメッセージを通知
        val receiveMessageResponse = ReceiveMessageResponse.newBuilder()
            .setChatMessage(chatMessage)
            .setFromUser(fromUserGrpc)
            .setToUser(toUserGrpc)
            .build()

        // TODO: ブロックされているユーザーからのメッセージを通知しない。

        // 送信先にメッセージを通知
        getReceiveMessageObverver(request.toUserId)?.onNext(receiveMessageResponse)
        getReceiveMessageObverver(fromUser.id)?.onNext(receiveMessageResponse)

        // プッシュ通知
        if (toUser.isEnabledNotify) {
            val pushMessage: String = fromUser.name + " : " + request.message
            pushNotifyService.send(request.toUserId, pushMessage)
        }

        val response = Empty.newBuilder().build()
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }

    override fun receiveMessage(request: Empty?, responseObserver: StreamObserver<ReceiveMessageResponse>?) {

        // 参考記事
        // https://qiita.com/Keiwa/items/b5218e425cddad8b9473#server-side-streaming

        if (responseObserver == null) {
            return
        }

        val authUser = GrpcConstant.AUTH_USER_CONTEXT_KEY.get()

        if (receiveMessageObserverMap.containsKey(authUser.id)) {
            receiveMessageObserverMap.replace(authUser.id, responseObserver)
        } else {
            receiveMessageObserverMap.put(authUser.id, responseObserver)
        }
    }

    override fun getAllMessages(
        request: AllChatMessageRequest?,
        responseObserver: StreamObserver<AllChatMessageResponse>?) {

        if (request == null || responseObserver == null) {
            return
        }

        val authUser = GrpcConstant.AUTH_USER_CONTEXT_KEY.get()

        val messages: List<com.talking.data.entity.ChatMessage> =
            chatMessageService.findAllMessagesForSpecificUser(authUser.id, request.minMessageId)

        val chatMessages = ArrayList<ChatMessage>()
        for (message in messages) {
            val chatMessage = ChatConverter.toGrpcChatMessage(message)
            chatMessages.add(chatMessage)
        }

        // メッセージの相手のユーザーの一覧を取得。
            // 最後にメッセージをやりとりした日時はクライアントで処理して保存すればいいかな。
            // 新規メッセージを追加するたびに更新すればいいかな。
        val users: List<User> = userService.findMessageFriends(authUser.id)
        val grpcUsers: List<com.talking.grpc.common.User> =
            users.map {
                userConverterService.toGrpcUser(it)
            }

        val response = AllChatMessageResponse.newBuilder()
            .addAllMessages(chatMessages)
            .addAllUsers(grpcUsers)
            .build()

        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }

    private fun getReceiveMessageObverver(userId: Long) : StreamObserver<ReceiveMessageResponse>? {

        val receiveMessageObserver = receiveMessageObserverMap[userId] ?: return null

        if (receiveMessageObserver is ServerCallStreamObserver<ReceiveMessageResponse> &&
                receiveMessageObserver.isCancelled) {
            receiveMessageObserverMap.remove(userId)
            return null
        }
        return receiveMessageObserver
    }
}