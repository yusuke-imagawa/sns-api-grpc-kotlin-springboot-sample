package com.talking.application

import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

@Service
class CacheService {

    private val callingMinutesCache = Caffeine.newBuilder()
        .expireAfterWrite(3, TimeUnit.DAYS).build<Pair<Long, String>, Int>()

    private val chatMessageCountCache = Caffeine.newBuilder()
        .expireAfterWrite(3, TimeUnit.DAYS).build<Pair<Long, String>, Int>()

    /**
     * 当日の通話の無料利用時間(分)を取得
     */
    fun getCallingMinutesInToday(userId: Long) : Int {
        return callingMinutesCache.getIfPresent(getUserIdDatePair(userId)) ?: 0
    }

    /**
     * 当日の通話の無料利用時間(分)をインクリメント
     */
    fun incrementCallingMinutesInToday(userId: Long) {
        val minutes = getCallingMinutesInToday(userId)
        callingMinutesCache.put(getUserIdDatePair(userId), minutes + 1)
    }

    /**
     * 当日のチャットのメッセージ送信数を取得
     */
    fun getChatMessageCountInToday(userId: Long) : Int {
        return chatMessageCountCache.getIfPresent(Pair(userId, getTodayString())) ?: 0
    }

    /**
     * 当日のチャットのメッセージ送信数をインクリメント
     */
    fun incrementChatMessageCountInToday(userId: Long) {
        val messageCount = getChatMessageCountInToday(userId)
        callingMinutesCache.put(getUserIdDatePair(userId), messageCount + 1)
    }

    private fun getTodayString() : String {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }

    private fun getUserIdDatePair(userId: Long) : Pair<Long, String> {
        return Pair(userId, getTodayString())
    }
}
