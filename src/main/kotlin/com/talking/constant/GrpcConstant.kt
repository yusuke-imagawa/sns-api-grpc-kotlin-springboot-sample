package com.talking.constant

import com.talking.data.entity.User
import io.grpc.Context
import io.grpc.Metadata.ASCII_STRING_MARSHALLER


/**
 * Created by imagawa on 2016/05/26.
 */
object GrpcConstant {

    val AUTH_USER_CONTEXT_KEY: Context.Key<User> = Context.key("authUser")

    val API_TOKEN_METADATA_KEY: io.grpc.Metadata.Key<String> =
        io.grpc.Metadata.Key.of("api_token", ASCII_STRING_MARSHALLER)

    val USER_ID_METADATA_KEY: io.grpc.Metadata.Key<String> =
        io.grpc.Metadata.Key.of("user_id", ASCII_STRING_MARSHALLER)
}
