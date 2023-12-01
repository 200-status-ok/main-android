package com.example.haminjast.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey
    val id: Long,
    val content: String,
    val contentType: String, //TODO enum
    val date: Long,
    val status: String, //TODO enum
    val senderID: Long,
    val conversationID: Long,
    val seqNumber: Long
) {
    companion object {
        fun fromResponse(messageResponse: MessageResponse): MessageEntity {
            return MessageEntity(
                id = messageResponse.id,
                content = messageResponse.content,
                contentType = messageResponse.contentType,
                date = messageResponse.date,
                status = messageResponse.status,
                senderID = messageResponse.senderID,
                conversationID = messageResponse.conversationID,
                seqNumber = messageResponse.seqNumber
            )
        }

        fun fromResponse2(messageResponse: ConversationHistoryResponse.ConversationHistoryResItem.Message): MessageEntity { //TODO remove
            return MessageEntity(
                id = messageResponse.id.toLong(),
                content = messageResponse.content,
                contentType = messageResponse.type,
                date = messageResponse.updatedAt.toLong(),
                status = messageResponse.status,
                senderID = messageResponse.senderId.toLong(),
                conversationID = messageResponse.conversationId.toLong(),
                seqNumber = 0
            )
        }
    }
}