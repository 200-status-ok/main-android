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
//                date = messageResponse.date,//TODO Unix
                date = System.currentTimeMillis(),
                status = messageResponse.status,
                senderID = messageResponse.senderID,
                conversationID = messageResponse.conversationID,
                seqNumber = messageResponse.seqNumber
            )
        }

        fun fromConversationHistoryResponseMessage(messageResponse: ConversationHistoryResponse.Message): MessageEntity { //TODO remove
//            val date = DateFormatSymbols("YYYY-MM-DDThh:mm:ss.sTZD").parse(messageResponse.updatedAt)
//            val l = LocalDate.parse(messageResponse.updatedAt, DateTimeFormatter.ofPattern("YYYY-MM-DDThh:mm:ss.sTZD"))
//            val unix = l.atStartOfDay(ZoneId.systemDefault()).toInstant().epochSecond
            return MessageEntity(
                id = messageResponse.id,
                content = messageResponse.content,
                contentType = messageResponse.type,
                date = System.currentTimeMillis(),
                status = messageResponse.status,
                senderID = messageResponse.senderId.toLong(),
                conversationID = messageResponse.conversationId.toLong(),
                seqNumber = 0
            )
        }

        fun fromConversationCoverResponseLastMessage(lastMessage: ConversationCoverResponse.ConversationCoverResponseItem.LastMessage): MessageEntity{
            return MessageEntity(
                id = lastMessage.id,
                content = lastMessage.content,
                contentType = lastMessage.type,
//                date = lastMessage.createdAt.toLong(),
                date = 8464644864L,
                status = lastMessage.status,
                senderID = lastMessage.senderId.toLong(),
                conversationID = lastMessage.conversationId.toLong(),
                seqNumber = 0,//TODO
            )
        }

        fun fromMessageReceivedUpdate(messageReceivedUpdate: MessageReceivedUpdate): MessageEntity {
            return MessageEntity(
                id = messageReceivedUpdate.id,
                content = messageReceivedUpdate.content,
                contentType = messageReceivedUpdate.type,
//                date = messageReceivedUpdate.time.toLong(), TODO UNIX
                date=System.currentTimeMillis(),
                status = messageReceivedUpdate.status,
                senderID = messageReceivedUpdate.senderId.toLong(),
                conversationID = messageReceivedUpdate.conversationId.toLong(),
                seqNumber = 0 //TODO
            )
        }
    }
}