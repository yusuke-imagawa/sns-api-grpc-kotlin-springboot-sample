package com.talking.application.pushnotify

import com.eatthepath.pushy.apns.ApnsClientBuilder
import com.eatthepath.pushy.apns.auth.ApnsSigningKey
import com.eatthepath.pushy.apns.util.ApnsPayloadBuilder
import com.eatthepath.pushy.apns.util.SimpleApnsPushNotification
import com.talking.application.EnvironmentService
import com.talking.data.entity.PushTokenIos
import com.talking.data.service.PushTokenIosService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Service


@Service
class IosPushNotifyService {

    companion object {
        private const val APNS_AUTH_KEY_PATH = "apns_certificate/apns_auth_key.p8"
        private const val PROD_APP_ID = "com.talking.talking-sns"
        private const val DEV_APP_ID = "com.talking.talking-sns.dev"
        private const val TEAM_ID = "3BPALQ9M7M"
        private const val KEY_ID = "62WL6FFTXV"
    }

    @Autowired
    private lateinit var environmentSerivice: EnvironmentService

    @Autowired
    private lateinit var pushTokenIosService: PushTokenIosService

    @Autowired
    private lateinit var resourceLoader: ResourceLoader

    /**
     * iOSの端末にプッシュ通知を送信します
     * @param toUserId
     * @param message
     */
    fun send(
        toUserId: Long,
        message: String) {

        val isSandBox: Boolean = environmentSerivice.isDevelop

        val pushTokenIosList: List<PushTokenIos> =
            pushTokenIosService.findByUserId(toUserId)
        for (pushTokenIos in pushTokenIosList) {
            val deviceToken = pushTokenIos.deviceToken ?: continue
            sendPushNotify(message, deviceToken, isSandBox)
        }
    }

    /**
     * プッシュ通知を送信します
     * @param message
     * @param deviceToken
     * @param isSandbox
     * @return
     */
    private fun sendPushNotify(
        message: String,
        deviceToken: String,
        isSandbox: Boolean) {

        try {
            // 通知クライアントを作成
            val apnsHost =
                if (isSandbox) {
                    ApnsClientBuilder.DEVELOPMENT_APNS_HOST
                } else {
                    ApnsClientBuilder.PRODUCTION_APNS_HOST
                }

            val appId =
                if (isSandbox) {
                    DEV_APP_ID
                } else {
                    PROD_APP_ID
                }

            val apnsClient = ApnsClientBuilder()
                .setApnsServer(apnsHost)
                .setSigningKey(
                    ApnsSigningKey.loadFromInputStream(
                        // .fileだとjar内でファイルを取得できない。inputStreamだと取得できる。
                        resourceLoader.getResource("classpath:$APNS_AUTH_KEY_PATH").inputStream,
                        TEAM_ID, KEY_ID
                    )
                ).build()

            // ペイロードを作成
            val payloadBuilder = ApnsPayloadBuilder()
            payloadBuilder.setAlertBody(message)
            val payload = payloadBuilder.buildWithDefaultMaximumLength()

            // 通知
            val pushNotification = SimpleApnsPushNotification(deviceToken, appId, payload)
            apnsClient.sendNotification(pushNotification)

        } catch (e: Exception) {
            // TODO: エラーログに書き込む。
            e.printStackTrace()
        }
    }
}