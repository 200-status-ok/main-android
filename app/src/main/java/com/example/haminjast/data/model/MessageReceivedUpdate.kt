package com.example.haminjast.data.model


import com.google.gson.annotations.SerializedName

data class MessageReceivedUpdate(
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
    @SerializedName("status")
    val status: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("type")
    val type: String
)