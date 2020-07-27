package com.talking.application

import com.talking.constant.RestApiConstant
import com.talking.data.entity.User
import com.talking.data.entity.UserStatus
import com.talking.data.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.servlet.http.HttpServletRequest

@Component
class HttpHeaderService {

    @Autowired(required = true)
    private val userService: UserService? = null

    @Autowired(required = true)
    private val request: HttpServletRequest? = null

    /**
     * 呼び出しもとのクライアントがiOSアプリであるか判定します
     * @return
     */
    val isIosApp: Boolean
        get() {
            val userToken = request!!.getHeader(RestApiConstant.REQUEST_HEADER_KEY_USER_AGENT)
            return RestApiConstant.REQUEST_HEADER_VALUE_USER_AGENT_IOS == userToken
        }

    /**
     * クライアントのuserTokenを返します
     * userTokenを取得できない場合、nullを返します
     * @return
     */
    val userToken: String
        get() = request!!.getHeader(RestApiConstant.REQUEST_HEADER_KEY_USER_TOKEN)

    /**
     * クライアントのuserIdを返します
     * userIdを取得できない場合、nullを返します
     * @return
     */
    val userId: Long?
        get() {
            val userIdStr = request!!.getHeader(RestApiConstant.REQUEST_HEADER_KEY_USER_ID) ?: return null
            try {
                return userIdStr.toLong()
            } catch (e: NumberFormatException) {
                return null
            }

        }

    /**
     * クライアントでログインしているユーザーがいる場合、Userを返します
     * 未ログインのクライアントである場合、nullを返します
     * @return
     */
    val loginUser: User?
        get() {
            try {
                val loginUserId = userId ?: return null

                val user = userService!!.findById(loginUserId)

                if (user!!.apiToken != userToken) {
                    return null
                }

                return if (user.status !== UserStatus.ENABLE) {
                    null
                } else user

            } catch (e: Exception) {
                println()
                return null
            }
        }
}
