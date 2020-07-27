package com.talking.api.grpc

import com.google.protobuf.Empty
import com.talking.api.grpc.interceptor.AuthInterceptor
import com.talking.constant.GrpcConstant
import com.talking.data.entity.Block
import com.talking.data.service.BlockService
import com.talking.data.service.UserService
import com.talking.grpc.block.BlockRequest
import com.talking.grpc.block.BlockServiceGrpc
import io.grpc.Status
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService
import org.springframework.beans.factory.annotation.Autowired

@GRpcService(interceptors = [AuthInterceptor::class])
class BlockGrpcService: BlockServiceGrpc.BlockServiceImplBase() {

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var blockService: BlockService

    override fun block(request: BlockRequest?, responseObserver: StreamObserver<Empty>?) {

        if (request == null || responseObserver == null) {
            return
        }
        val fromUser = GrpcConstant.AUTH_USER_CONTEXT_KEY.get()

        val blockUser = userService.findById(request.toUserId)
        if (blockUser == null) {
            responseObserver.onError(Status.INVALID_ARGUMENT.asRuntimeException())
            return
        }

        if (fromUser.id == request.toUserId) {
            responseObserver.onError(Status.INVALID_ARGUMENT.asRuntimeException())
            return
        }

        blockService.save(
            Block(
                fromUser = fromUser,
                blockUser = blockUser
            )
        )
        responseObserver.onNext(Empty.newBuilder().build())
        responseObserver.onCompleted()
    }
}