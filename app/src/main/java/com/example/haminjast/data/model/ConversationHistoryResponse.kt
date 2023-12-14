package com.example.haminjast.data.model


import com.google.gson.annotations.SerializedName

data class ConversationHistoryResponse(
    @SerializedName("messages")
    val messages: List<Message>,
    @SerializedName("user_id")
    val userId: Int
) {
    data class Message(
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