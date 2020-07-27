package com.talking.api.grpc.interceptor

import com.talking.constant.GrpcConstant
import com.talking.data.service.UserService
import io.grpc.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AuthInterceptor : ServerInterceptor {

    @Autowired
    private lateinit var userService: UserService

    // 参考ソース:
    // https://github.com/saturnism/grpc-by-example-java/blob/master/metadata-context-example/src/main/java/com/example/grpc/server/JwtServerInterceptor.java

    override fun <ReqT : Any?, RespT : Any?> interceptCall(
        call: ServerCall<ReqT, RespT>,
        headers: Metadata,
        next: ServerCallHandler<ReqT, RespT>
    ): ServerCall.Listener<ReqT> {

        val userId = headers.get(GrpcConstant.USER_ID_METADATA_KEY)?.toLong() ?:
                throw StatusRuntimeException(Status.UNAUTHENTICATED, headers)

        val apiToken = headers.get(GrpcConstant.API_TOKEN_METADATA_KEY) ?:
                throw StatusRuntimeException(Status.UNAUTHENTICATED, headers)

        val user = userService.findByIdAndToken(userId, apiToken) ?:
            throw StatusRuntimeException(Status.UNAUTHENTICATED, headers)

        val ctx = Context.current().withValue(GrpcConstant.AUTH_USER_CONTEXT_KEY, user)
        return Contexts.interceptCall(ctx, call, headers, next)
    }
}