package com.example.haminjast.ui.model

import android.util.Log
import com.example.haminjast.User
import com.example.haminjast.data.model.MessageEntity
import com.example.haminjast.ui.screen.chat.component.MessageType

data class MessageUI(
    val id: Long,
    val content: String,
    val messageContentType: MessageContentType,
    val date: String,
    val status: MessageStatus,
    val type: MessageType,
    val senderID: Long,
    val conversationID: Long,
    val seqNumber: Long
) {
    companion object {
        fun fromEntity(entity: MessageEntity): MessageUI {
            Log.d("modar","User.id ${User.id} senderID ${entity.senderID}");
            return MessageUI(
                id = entity.id,
                content = entity.content,
                messageContentType = MessageContentType.valueOf(entity.contentType.replaceFirstChar { it.uppercase() }),
                date = entity.date.toString(), //TODO
                status = MessageStatus.valueOf(entity.status.replaceFirstChar { it.uppercase() }),
                type = if (entity.senderID == User.id) MessageType.Outgoing else MessageType.Incoming,
                senderID = entity.senderID,
                conversationID = entity.conversationID,
                seqNumber = entity.seqNumber
            )
        }
    }
}

enum class MessageContentType {
    Text,
    Image,
    Location
}

enum class MessageStatus {
    Pending,
    Unread,
    Read,
    Failed;
}