package com.example.haminjast.data.model


import com.google.gson.annotations.SerializedName

class ConversationCoverResponse : ArrayList<ConversationCoverResponse.ConversationCoverResponseItem>(){
    data class ConversationCoverResponseItem(
        @SerializedName("id")
        val id: Int,
        @SerializedName("image_url")
        val imageUrl: String,
        @SerializedName("is_owner")
        val isOwner: Boolean,
        @SerializedName("last_message")
        val lastMessage: LastMessage,
        @SerializedName("name")
        val name: String,
        @SerializedName("poster_id")
        val posterId: Int
    ) {
        data class LastMessage(
            @SerializedName("content")
            val content: String,
            @SerializedName("conversation_id")
            val conversationId: Int,
            @SerializedName("created_at")
            val createdAt: String,
            @SerializedName("deleted_at")
            val deletedAt: String,
            @SerializedName("id")
            val id: Long,
            @SerializedName("is_send")
            val isSend: Boolean,
            @SerializedName("receiver_id")
            val receiverId: Int,
            @SerializedName("sender_id")
            val senderId: Int,
            @SerializedName("status")
            val status: String,
            @SerializedName("type")
            val type: String,
            @SerializedName("updated_at")
            val updatedAt: String
        )
    }
}