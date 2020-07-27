package com.talking.data.repository

import com.talking.data.entity.PushTokenIos
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PushTokenIosRepository : JpaRepository<PushTokenIos, Long> {
    fun findByUserIdAndDeviceToken(userId: Long, deviceToken: String): List<PushTokenIos>

    fun findByUserId(userId: Long): List<PushTokenIos>
}
