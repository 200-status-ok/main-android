package com.example.haminjast.ui.model

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
    val lastReadMessageSeqNumber: Int
) {
    companion object {
        fun fromConversationCoverEntityAndMessageEntity(
            conversationCoverEntity: ConversationCoverEntity,
            messageEntity: MessageEntity?
        ): ConversationCoverUI {
            var unreadCount = 0
            val messageUI = messageEntity?.let {
                unreadCount =
                    (it.seqNumber - conversationCoverEntity.lastReadMessageSeqNumber).toInt()
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
                lastReadMessageSeqNumber = conversationCoverEntity.lastReadMessageSeqNumber
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
                lastReadMessageSeqNumber = conversationCoverEntity.lastReadMessageSeqNumber
            )
        }
    }
}