package com.talking.api.grpc

import com.talking.api.grpc.converter.CommonConverter
import com.talking.api.grpc.converter.UserConverterService
import com.talking.api.grpc.interceptor.AuthInterceptor
import com.talking.constant.GrpcConstant
import com.talking.data.entity.User
import com.talking.data.service.BlockService
import com.talking.data.service.UserService
import com.talking.grpc.users.*
import io.grpc.Status
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDateTime

@GRpcService(interceptors = [AuthInterceptor::class])
class UserGrpcService: UserServiceGrpc.UserServiceImplBase() {

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var blockService: BlockService

    @Autowired
    private lateinit var userConverterService: UserConverterService

    companion object {
        // APIの取得件数
        private const val MAX_TIMELINE_LIMIT = 100
    }

    override fun getUsers(request: GetUsersRequest?, responseObserver: StreamObserver<GetUsersResponse>?) {

        if (request == null || responseObserver == null) {
            return
        }

        val authUser = GrpcConstant.AUTH_USER_CONTEXT_KEY.get()

        // ユーザーの「オンラインになった日時」を現在日時で更新
        authUser.lastOnlineDatetime = LocalDateTime.now()
        userService.save(authUser)

        // blockしたユーザー、apiを呼び出したユーザーをユーザーリストから省く
        val notInIds: MutableList<Long> =
            blockService.findByFromUserId(authUser.id).map { it.id }.toMutableList()
        notInIds.add(authUser.id)

        val maxOnlineDatetime =
            if (request.hasMaxOnlineDatetime()) {
                CommonConverter.toLocalDateTime(request.maxOnlineDatetime)
            } else {
                LocalDateTime.now()
            }

        val limit =
            if (request.limit <= 0 || request.limit > MAX_TIMELINE_LIMIT) {
                MAX_TIMELINE_LIMIT
            } else {
                request.limit
            }

        // ユーザーを取得
        val users: List<User> = userService.findUsers(notInIds, limit, maxOnlineDatetime)
        val grpcUsers: List<com.talking.grpc.common.User> =
            users.map {
                userConverterService.toGrpcUser(it)
            }

        val response = GetUsersResponse.newBuilder()
            .addAllUsers(grpcUsers)
            .build()

        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }

    override fun getUserById(request: GetUserRequest?, responseObserver: StreamObserver<GetUserResponse>?) {

        if (request == null || responseObserver == null) {
            return
        }

        val user = userService.findById(request.userId.toLong())
        if (user == null) {
            responseObserver.onError(Status.INVALID_ARGUMENT.asRuntimeException())
            return
        }

        val grpcUser: com.talking.grpc.common.User = userConverterService.toGrpcUser(user)

        val response = GetUserResponse.newBuilder()
            .setUser(grpcUser)
            .build()

        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}