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
        fun fromConversationHistoryResponseMessage(messageResponse: ConversationHistoryResponse.Message): MessageEntity { //TODO remove
            return MessageEntity(
                id = messageResponse.id,
                content = messageResponse.content,
                contentType = messageResponse.type,
                date = messageResponse.createdAt,
                status = messageResponse.status,
                senderID = messageResponse.senderId.toLong(),
                conversationID = messageResponse.conversationId.toLong(),
                seqNumber = messageResponse.sequenceNo.toLong()
            )
        }

        fun fromConversationCoverResponseLastMessage(lastMessage: ConversationCoverResponse.ConversationCoverResponseItem.LastMessage): MessageEntity{
            return MessageEntity(
                id = lastMessage.id,
                content = lastMessage.content,
                contentType = lastMessage.type,
                date = lastMessage.createdAt,
                status = lastMessage.status,
                senderID = lastMessage.senderId.toLong(),
                conversationID = lastMessage.conversationId.toLong(),
                seqNumber = lastMessage.sequenceNo.toLong(),//TODO
            )
        }

        fun fromMessageReceivedUpdate(messageReceivedUpdate: MessageUpdate): MessageEntity {
            return MessageEntity(
                id = messageReceivedUpdate.id,
                content = messageReceivedUpdate.content,
                contentType = messageReceivedUpdate.type,
                date = messageReceivedUpdate.time,
                status = messageReceivedUpdate.status,
                senderID = messageReceivedUpdate.senderId.toLong(),
                conversationID = messageReceivedUpdate.conversationId.toLong(),
                seqNumber = messageReceivedUpdate.sequenceNo.toLong()
            )
        }
    }
}