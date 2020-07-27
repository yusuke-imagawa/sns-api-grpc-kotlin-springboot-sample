package com.talking.data.service

import com.talking.data.entity.CallingLog
import com.talking.data.entity.CallingStatus
import com.talking.data.repository.CallingLogRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.persistence.EntityManager

@Service
@Transactional
class CallingLogService {

    @Autowired
    private lateinit var entityManager: EntityManager

    @Autowired
    private lateinit var callingLogRepository: CallingLogRepository

    fun save(callingLog: CallingLog) : CallingLog {
        return callingLogRepository.save(callingLog)
    }

    fun findByFromIdAndToIdAndStatus(fromId: Long, toId: Long, statuses: List<CallingStatus>): List<CallingLog> {
        val query = "SELECT * FROM calling_logs " +
                "WHERE from_user_id = :from_user_id AND to_user_id = :to_user_id AND status IN :statuses"

        // resultクラスをLongにするとexceptionが発生する。BigDecimalだと問題ないらしい。
        val resultList = entityManager.createNativeQuery(query, CallingLog::class.java)
            .setParameter("from_user_id", fromId)
            .setParameter("to_user_id", toId)
            .setParameter("statuses", statuses.map { it.value })
            .resultList

        val callingLogs: MutableList<CallingLog> = mutableListOf()
        for (callingLog in resultList) {
            if (callingLog is CallingLog) {
                callingLogs.add(callingLog)
            }
        }
        return callingLogs
    }

    fun findStartingOrTalkingLogs(
        userId: Long,
        inTalkingContinueMinute: Int): List<CallingLog> {

        val query = "SELECT * FROM calling_logs " +
                "WHERE (from_user_id = :from_user_id OR to_user_id = :to_user_id)" +
                "AND last_from_user_continue_datetime >= :min_from_user_continue_datetime " +
                "AND last_to_user_continue_datetime >= :min_to_user_continue_datetime " +
                "AND status IN :statuses"

        // 最後に「continue APIが呼び出された確認された日時」が○○分以内であること
        val minUserContinueDatetime = Calendar.getInstance()
        minUserContinueDatetime.time = Date()
        minUserContinueDatetime.add(Calendar.MINUTE, -inTalkingContinueMinute)

        val statuses = listOf(CallingStatus.STARTING, CallingStatus.TALKING)

        // resultクラスをLongにするとexceptionが発生する。BigDecimalだと問題ないらしい。
        val resultList = entityManager.createNativeQuery(query, CallingLog::class.java)
            .setParameter("from_user_id", userId)
            .setParameter("to_user_id", userId)
            .setParameter("min_from_user_continue_datetime", minUserContinueDatetime.time)
            .setParameter("min_to_user_continue_datetime", minUserContinueDatetime.time)
             .setParameter("statuses", statuses.map { it.value })
            .resultList

        val callingLogs: MutableList<CallingLog> = mutableListOf()
        for (callingLog in resultList) {
            if (callingLog is CallingLog) {
                callingLogs.add(callingLog)
            }
        }
        return callingLogs
    }

    /**
     * 指定したuserId, Statusのレコードを取得します。
     */
    fun findByUserIdAndStatus(userId: Long, statuses: List<CallingStatus>) : List<CallingLog> {
        val query = "SELECT * FROM calling_logs " +
                "WHERE (from_user_id = :from_user_id OR to_user_id = :to_user_id) AND status IN :statuses"

        // resultクラスをLongにするとexceptionが発生する。BigDecimalだと問題ないらしい。
        val resultList = entityManager.createNativeQuery(query, CallingLog::class.java)
            .setParameter("from_user_id", userId)
            .setParameter("to_user_id", userId)
            .setParameter("statuses", statuses.map { it.value })
            .resultList

        val callingLogs: MutableList<CallingLog> = mutableListOf()
        for (callingLog in resultList) {
            if (callingLog is CallingLog) {
                callingLogs.add(callingLog)
            }
        }
        return callingLogs
    }
}