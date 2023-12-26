package com.example.haminjast.ui.model

import com.example.haminjast.TimeUtils
import com.example.haminjast.data.model.ConversationCoverEntity
import com.example.haminjast.data.model.MessageEntity

data class ConversationCoverUI(
    val id: Long,
    val title: String,
    val imageUrl: String,
    val lastMessage: MessageUI?, //TODO
    val posterID: Long,
    val isOwner: Boolean,
    val unreadCount: Int,
    val lastReadMessageSeqNumber: Int,
    val lastMessageDate:String?
) {
    companion object {
        fun fromConversationCoverEntityAndMessageEntity(
            conversationCoverEntity: ConversationCoverEntity,
            lastMessageEntity: MessageEntity?
        ): ConversationCoverUI {
            var unreadCount = 0
            val messageUI = lastMessageEntity?.let {
                unreadCount =
                    if (lastMessageEntity.senderID == com.example.haminjast.User.id) {
                        0
                    } else {
                        (it.seqNumber - conversationCoverEntity.lastReadMessageSeqNumber).toInt()
                    }
                MessageUI.fromEntity(it)
            }
            return ConversationCoverUI(
                id = conversationCoverEntity.id,
                title = conversationCoverEntity.title,
                imageUrl = conversationCoverEntity.imageUrl,
                lastMessage = messageUI,
                posterID = conversationCoverEntity.posterID,
                isOwner = conversationCoverEntity.isOwner,
                unreadCount = unreadCount,
                lastReadMessageSeqNumber = conversationCoverEntity.lastReadMessageSeqNumber,
                lastMessageDate = lastMessageEntity?.let { TimeUtils.timeStampToSemantics(it.date) }
            )
        }

        fun fromConversationCoverEntity(conversationCoverEntity: ConversationCoverEntity): ConversationCoverUI {
            return ConversationCoverUI(
                id = conversationCoverEntity.id,
                title = conversationCoverEntity.title,
                imageUrl = conversationCoverEntity.imageUrl,
                lastMessage = null,
                posterID = conversationCoverEntity.posterID,
                isOwner = conversationCoverEntity.isOwner,
                unreadCount = 0,
                lastReadMessageSeqNumber = conversationCoverEntity.lastReadMessageSeqNumber,
                lastMessageDate = null
            )
        }
    }
}