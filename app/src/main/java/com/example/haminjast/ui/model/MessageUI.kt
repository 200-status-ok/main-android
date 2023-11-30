package com.example.haminjast.ui.model

import com.example.haminjast.data.model.MessageEntity

data class MessageUI(
    val messageID: Long,
    val content: String,
    val contentType: String,
    val date: Long,
    val status: Long,
    val senderID: Long,
    val conversationID: Long,
    val seqNumber: Long
){
    companion object {
        fun fromEntity(entity: MessageEntity): MessageUI {
            return MessageUI(
                entity.id,
                entity.content,
                entity.contentType,
                entity.date,
                entity.status,
                entity.senderID,
                entity.conversationID,
                entity.seqNumber
            )
        }
    }
}