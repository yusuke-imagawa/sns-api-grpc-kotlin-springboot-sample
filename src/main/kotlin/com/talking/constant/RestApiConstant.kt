package com.talking.constant

/**
 * Created by imagawa on 2016/05/26.
 */
object RestApiConstant {

    /**
     * リクエストヘッダーのキー_iOSアプリのバージョン
     */
    // val REQUEST_HEADER_KEY_IOS_APP_VERSION = "X-IosAppVersion"

    /**
     * リクエストヘッダーのキー_ユーザートークン
     */
    val REQUEST_HEADER_KEY_USER_TOKEN = "X-ApiToken"

    /**
     * リクエストヘッダーのキー_ユーザーID
     */
    val REQUEST_HEADER_KEY_USER_ID = "X-UserId"

    /**
     * リクエストヘッダーのキー_ユーザーエージェント(iOSアプリ or Androidアプリのどちらから呼び出されたものか判定するために使う)
     */
    val REQUEST_HEADER_KEY_USER_AGENT = "User-Agent"

    val REQUEST_HEADER_VALUE_USER_AGENT_IOS = "App-iOS"

}
