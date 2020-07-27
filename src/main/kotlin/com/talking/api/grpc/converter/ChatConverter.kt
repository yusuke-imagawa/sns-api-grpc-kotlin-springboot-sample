package com.talking.api.grpc.converter

import com.talking.grpc.chat.ChatMessage

class ChatConverter {

    companion object {
        fun toGrpcChatMessage(chatMessageEntity: com.talking.data.entity.ChatMessage) : ChatMessage {
            return ChatMessage.newBuilder()
                    .setId(chatMessageEntity.id)
                    .setFromUserId(chatMessageEntity.fromUser.id)
                    .setToUserId(chatMessageEntity.toUser.id)
                    .setMessage(chatMessageEntity.message)
                    .setDate(CommonConverter.toGrpcTimestamp(chatMessageEntity.created))
                    .build()
        }
    }
}