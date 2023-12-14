package com.example.haminjast.data.model


import com.google.gson.annotations.SerializedName

data class SendMessageResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("send_message")
    val sendMessage: SendMessage
) {
    data class SendMessage(
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