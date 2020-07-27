package com.talking.application

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*

fun generateApiToken() : String {
    return UUID.randomUUID().toString()
}

// BCryptPasswordEncoderの参考記事(scala):
// https://qiita.com/ponkotuy/items/1a703b866ddf5c9fe80c

val bcryptEncoder = BCryptPasswordEncoder()

/**
 * passwordを生成します。
 * @param password String
 * @return String
 */
fun generatePasswordHash(password: String) : String {
    return bcryptEncoder.encode(password)
}

/**
 * パスワードとパスワードhashがマッチするか判定します。
 * @param password String
 * @param passwordHash String
 * @return Boolean
 */
fun isMatchPassword(password: String, passwordHash: String) : Boolean {
    return bcryptEncoder.matches(password, passwordHash)
}




