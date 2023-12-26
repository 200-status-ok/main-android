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
        @SerializedName("time")
        val time: Long,
        @SerializedName("type")
        val type: String
    )
}