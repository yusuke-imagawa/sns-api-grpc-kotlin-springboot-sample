package com.talking.api.grpc

import com.talking.api.grpc.converter.CommonConverter
import com.talking.api.grpc.converter.UserConverterService
import com.talking.application.generateApiToken
import com.talking.data.entity.User
import com.talking.data.service.UserService
import com.talking.grpc.user.AccountRegisterServiceGrpc
import com.talking.grpc.user.RegisterUserRequest
import com.talking.grpc.user.RegisterUserResponse
import io.grpc.Status
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService
import org.springframework.beans.factory.annotation.Autowired

@GRpcService
class AccountRegisterGrpcService: AccountRegisterServiceGrpc.AccountRegisterServiceImplBase() {

    @Autowired
    private lateinit var userService: UserService

    override fun register(request: RegisterUserRequest?, responseObserver: StreamObserver<RegisterUserResponse>?) {

        if (request == null || responseObserver == null) {
            return
        }

        if (request.name.isBlank()) {
            responseObserver.onError(Status.INVALID_ARGUMENT.asRuntimeException())
            return
        }

        val userByName: User? = userService.getByName(request.name.trim())
        if (userByName != null) {
            responseObserver.onError(
                Status.INVALID_ARGUMENT
                    .withDescription("既に登録されている名前です。")
                    .asRuntimeException())
            return
        }

        val user = User(
            name = request.name,
            genderType = UserConverterService.toDbGenderType(request.genderType),
            age = request.age,
            pr = request.pr,
            apiToken = generateApiToken()
        )
        userService.save(user)

        val userResponse = com.talking.grpc.common.User.newBuilder()
            .setId(user.id)
            .setName(user.name)
            .setGenderType(request.genderType)
            .setAge(user.age)
            .setPr(user.pr)
            .setLastOnlineDatetime(CommonConverter.toGrpcTimestamp(user.lastOnlineDatetime))
            .setCreated(CommonConverter.toGrpcTimestamp(user.created))
            .setModified(CommonConverter.toGrpcTimestamp(user.modified))
            .build()

        val response = RegisterUserResponse.newBuilder()
            .setApiToken(user.apiToken)
            .setUser(userResponse)
            .build()

        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}