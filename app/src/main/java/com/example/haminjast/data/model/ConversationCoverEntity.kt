package com.example.haminjast.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conversation_covers")
data class ConversationCoverEntity(
    @PrimaryKey
    val id:Long,
    val title:String,
    val imageUrl:String,
    val lastMessageId: Long,
    val posterID:Long,
    val isOwner:Boolean,
    val lastReadMessageSeqNumber:Int
){
    companion object{
        fun fromResponse(conversationCoverResponseItem: ConversationCoverResponse.ConversationCoverResponseItem):ConversationCoverEntity{
            return ConversationCoverEntity(
                id = conversationCoverResponseItem.id.toLong(),
                title = conversationCoverResponseItem.name,
                imageUrl = conversationCoverResponseItem.imageUrl,
                lastMessageId = conversationCoverResponseItem.lastMessage.id, //TODO
                posterID = conversationCoverResponseItem.posterId.toLong(),
                isOwner = conversationCoverResponseItem.isOwner,
                lastReadMessageSeqNumber = conversationCoverResponseItem.lastMessage.sequenceNo
            )
        }
    }
}