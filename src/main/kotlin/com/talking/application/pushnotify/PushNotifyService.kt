package com.talking.application.pushnotify

import com.talking.data.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * プッシュ通知処理をまとめたクラスです
 * Created by imagawa on 2016/08/03.
 */
@Service
class PushNotifyService {

    @Autowired
    private lateinit var iosPushNotifyService: IosPushNotifyService

    /**
     * プッシュ通知を送信します
     * @param toUserId
     * @param message
     */
    fun send(toUserId: Long, message: String) {
        // iOS端末にプッシュ通知を送信する
        iosPushNotifyService.send(toUserId, message)
    }
}