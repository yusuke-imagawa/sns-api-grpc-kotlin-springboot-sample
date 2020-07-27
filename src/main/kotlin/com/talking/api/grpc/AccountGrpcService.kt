package com.talking.api.grpc

import com.google.protobuf.Empty
import com.talking.api.grpc.interceptor.AuthInterceptor
import com.talking.constant.GrpcConstant
import com.talking.data.entity.UserStatus
import com.talking.data.service.UserService
import com.talking.grpc.user.AccountServiceGrpc
import com.talking.grpc.user.UpdateProfileRequest
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.util.StringUtils

@GRpcService(interceptors = [AuthInterceptor::class])
class AccountGrpcService: AccountServiceGrpc.AccountServiceImplBase() {

    @Autowired
    private lateinit var userService: UserService

    override fun updateProfile(
        request: UpdateProfileRequest?,
        responseObserver: StreamObserver<Empty>?) {

        if (request == null || responseObserver == null) {
            return
        }

        val authUser = GrpcConstant.AUTH_USER_CONTEXT_KEY.get()

        if (request.hasName()) {
            authUser.name = request.name.value
        }

        if (request.hasAge()) {
            authUser.age = request.age.value
        }

        if (request.hasPr() && !StringUtils.isEmpty(request.pr.value)) {
            authUser.pr = request.pr.value
        }
        userService.save(authUser)

        val response = Empty.newBuilder().build()
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }

    override fun leave(request: Empty?, responseObserver: StreamObserver<Empty>?) {

        if (responseObserver == null) {
            return
        }

        val authUser = GrpcConstant.AUTH_USER_CONTEXT_KEY.get()
        authUser.status = UserStatus.LEAVE
        userService.save(authUser)

        val response = Empty.newBuilder().build()
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}