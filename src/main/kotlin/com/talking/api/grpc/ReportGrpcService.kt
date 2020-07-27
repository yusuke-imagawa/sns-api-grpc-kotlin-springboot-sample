package com.talking.api.grpc

import com.google.protobuf.Empty
import com.talking.api.grpc.interceptor.AuthInterceptor
import com.talking.constant.GrpcConstant
import com.talking.data.entity.Report
import com.talking.data.service.ReportService
import com.talking.data.service.UserService
import com.talking.grpc.report.PostReportRequest
import com.talking.grpc.report.ReportServiceGrpc
import io.grpc.Status
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService
import org.springframework.beans.factory.annotation.Autowired

@GRpcService(interceptors = [AuthInterceptor::class])
class ReportGrpcService: ReportServiceGrpc.ReportServiceImplBase() {

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var reportService: ReportService

    override fun postReport(request: PostReportRequest?, responseObserver: StreamObserver<Empty>?) {

        if (request == null || responseObserver == null) {
            return
        }

        val authUser = GrpcConstant.AUTH_USER_CONTEXT_KEY.get()

        val toUser = userService.findById(request.toUserId)
        if (toUser == null) {
            responseObserver.onError(Status.INVALID_ARGUMENT.asRuntimeException())
            return
        }

        reportService.save(
            Report(
                targetUser = toUser,
                reporterUser = authUser,
                message = request.message
            )
        )

        val response = Empty.newBuilder().build()
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}