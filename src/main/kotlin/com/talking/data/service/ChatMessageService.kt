package com.talking.data.service

import com.talking.data.entity.ChatMessage
import com.talking.data.repository.ChatMessageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Service
@Transactional
class ChatMessageService {

    @Autowired
    private lateinit var entityManager: EntityManager

    @Autowired
    private lateinit var chatMessageRepository: ChatMessageRepository

    fun save(chatMessage: ChatMessage) : ChatMessage {
        return chatMessageRepository.save(chatMessage)
    }

    /**
     * 特定のユーザーに送信されたメッセージを取得します
     * ※ブロックしたユーザーとのメッセージは除きます
     * @param userId
     * @param minMessageId
     * @return
     */
    fun findAllMessagesForSpecificUser(userId: Long, minMessageId: Long): List<ChatMessage> {
        val query = "SELECT * FROM chat_messages WHERE " +
                " (from_user_id = :user_id1 OR to_user_id = :user_id2)" +
                " AND id >= :min_message_id" +
                " AND NOT EXISTS (" +
                "   SELECT * FROM blocks WHERE from_user_id = :user_id3 " +
                "     AND (block_user_id = chat_messages.to_user_id OR block_user_id = chat_messages.from_user_id)" +
                " )"

        val messagesResultList = entityManager.createNativeQuery(query, ChatMessage::class.java)
            .setParameter("user_id1", userId)
            .setParameter("user_id2", userId)
            .setParameter("user_id3", userId)
            .setParameter("min_message_id", minMessageId)
            .getResultList()

        val chatMessages: MutableList<ChatMessage> = mutableListOf()
        for (message in messagesResultList) {
            if (message is ChatMessage) {
                chatMessages.add(message)
            }
        }
        return chatMessages
    }
}