package com.talking.data.service

import com.talking.data.entity.GenderType
import com.talking.data.entity.User
import com.talking.data.entity.UserStatus
import com.talking.data.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import javax.persistence.EntityManager
import kotlin.random.Random

@Service
@Transactional
class UserService {

    @Autowired
    private lateinit var entityManager: EntityManager

    @Autowired
    private lateinit var userRepository: UserRepository

    fun save(user: User) : User {
        return userRepository.save(user)
    }

    fun findById(id: Long) : User? {
        return userRepository.findById(id).orElse(null)
    }

    fun findByIds(ids: List<Long>) : List<User> {
        return userRepository.findAllById(ids)
    }

    fun findByIdAndToken(id: Long, apiToken: String) : User? {
        val user = userRepository.findById(id).orElse(null) ?: return null

        if (user.apiToken != apiToken) {
            return null
        }
        return user
    }

    fun getByName(name: String) : User? {
        return userRepository.getByName(name)
    }

    /**
     * 使われていないユーザー名を生成します。
     * @return String
     */
    fun generateUniqueUserName() : String {
        var name: String = ""
        for (i in 1..10) {
            // name: user + 9桁の数値
            name = "user%09d".format(Random.nextInt(0, 999999999))
            val userByName: User? = getByName(name.trim())
            if (userByName == null) {
                break
            }
        }
        return name
    }

    /**
     * 指定したユーザーidのユーザーとメッセージのやりとりをしているユーザーのリストを返します。
     * @param userId
     * @return
     */
    fun findMessageFriends(userId: Long): List<User> {
        val query = " SELECT * FROM users WHERE " +
                "    /* ユーザーがメッセージを送信または受信している相手であること */" +
                "    ( " +
                "       EXISTS(SELECT * FROM chat_messages WHERE from_user_id = :user_id1 AND to_user_id = users.id)" +
                "       OR EXISTS(SELECT * FROM chat_messages WHERE to_user_id = :user_id2 AND from_user_id = users.id)" +
                "    )" +
                "    /* ユーザーがブロックしているユーザーでないこと */" +
                "    AND NOT EXISTS (" +
                "       SELECT * FROM blocks WHERE from_user_id = :user_id3 " +
                "       AND block_user_id = users.id" +
                "    )"

        // resultクラスをLongにするとexceptionが発生する。BigDecimalだと問題ないらしい。
        val resultList = entityManager.createNativeQuery(query, User::class.java)
            .setParameter("user_id1", userId)
            .setParameter("user_id2", userId)
            .setParameter("user_id3", userId)
            .getResultList()

        val users: MutableList<User> = mutableListOf()
        for (user in resultList) {
            if (user is User) {
                users.add(user)
            }
        }
        return users
    }

    fun findUsers(notInIds: List<Long>, limit: Int, maxOnlineDatetime: LocalDateTime) : List<User> {

        val query = """
            SELECT 
                *
            FROM
                users
            WHERE
                id NOT IN :not_in_ids
                AND status = :status
                AND last_online_datetime < :max_online_datetime    
            ORDER BY last_online_datetime desc
            LIMIT :limit
        """.trimIndent()

        // resultクラスをLongにするとexceptionが発生する。BigDecimalだと問題ないらしい。
        val resultList = entityManager.createNativeQuery(query, User::class.java)
            .setParameter("not_in_ids", notInIds)
            .setParameter("status", UserStatus.ENABLE.value)
            .setParameter("limit", limit)
            .setParameter("max_online_datetime", maxOnlineDatetime)
            .getResultList()

        val users: MutableList<User> = mutableListOf()
        for (user in resultList) {
            if (user is User) {
                users.add(user)
            }
        }
        return users
    }

    fun findUsers(notInIds: List<Long>, genderType: GenderType, limit: Int, maxOnlineDatetime: LocalDateTime) : List<User> {

        val query = """
            SELECT 
                *
            FROM
                users
            WHERE
                id NOT IN :not_in_ids
                AND gender_type = :gender_type
                AND status = :status
                AND last_online_datetime < :max_online_datetime    
            ORDER BY last_online_datetimedesc
            LIMIT :limit
        """.trimIndent()

        // resultクラスをLongにするとexceptionが発生する。BigDecimalだと問題ないらしい。
        val resultList = entityManager.createNativeQuery(query, User::class.java)
            .setParameter("not_in_ids", notInIds)
            .setParameter("gender_type", genderType.value)
            .setParameter("status", UserStatus.ENABLE)
            .setParameter("limit", limit)
            .setParameter("max_online_datetime", maxOnlineDatetime)
            .getResultList()

        val users: MutableList<User> = mutableListOf()
        for (user in resultList) {
            if (user is User) {
                users.add(user)
            }
        }
        return users
    }
}