package com.example.haminjast.ui.model

import com.example.haminjast.data.model.ConversationCoverEntity
import com.example.haminjast.data.model.MessageEntity

data class ConversationCoverUI(
    val id: Long,
    val title: String,
    val imageUrl: String,
    val lastMessageId: MessageUI, //TODO
    val posterID: Long,
    val isOwner: Boolean,
    val unreadCount: Int
) {
    companion object {
        fun fromConversationCoverEntityAndMessageEntity(
            conversationCoverEntity: ConversationCoverEntity,
            messageEntity: MessageEntity
        ): ConversationCoverUI {
            val messageUI = MessageUI.fromEntity(messageEntity)
            return ConversationCoverUI(
                conversationCoverEntity.id,
                conversationCoverEntity.title,
                conversationCoverEntity.imageUrl,
                messageUI,
                conversationCoverEntity.posterID,
                conversationCoverEntity.isOwner,
                (messageUI.seqNumber - conversationCoverEntity.lastReadMessageSeqNumber).toInt()
            )
        }
    }
}