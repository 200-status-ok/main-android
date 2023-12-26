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
        val createdAt: Long,
        @SerializedName("id")
        val id: Long,
        @SerializedName("receiver_id")
        val receiverId: Int,
        @SerializedName("sender_id")
        val senderId: Int,
        @SerializedName("sequence_no")
        val sequenceNo: Int,
        @SerializedName("status")
        val status: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("updated_at")
        val updatedAt: Long
    )
}