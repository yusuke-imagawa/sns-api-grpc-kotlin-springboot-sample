package com.talking.data.service

import com.talking.data.entity.PushTokenIos
import com.talking.data.repository.PushTokenIosRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PushTokenIosService {

    @Autowired
    private lateinit var pushTokenIosRepository: PushTokenIosRepository

    fun save(pushTokenIos: PushTokenIos) : PushTokenIos {
        return pushTokenIosRepository.save(pushTokenIos)
    }

    fun findByUserIdAndDeviceToken(userId: Long, deviceToken: String): List<PushTokenIos> {
        return pushTokenIosRepository.findByUserIdAndDeviceToken(userId, deviceToken)
    }

    fun findByUserId(userId: Long): List<PushTokenIos> {
        return pushTokenIosRepository.findByUserId(userId)
    }
}