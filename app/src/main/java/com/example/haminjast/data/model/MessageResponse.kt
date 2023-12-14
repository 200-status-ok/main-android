package com.example.haminjast.data.model

import com.google.gson.annotations.SerializedName


data class MessageResponse(
    @SerializedName("message_id")
    val id: Long,

    @SerializedName("content")
    val content: String,

    @SerializedName("content_type")
    val contentType: String,

    @SerializedName("date")
    val date: String,

    @SerializedName("status")
    val status: String,

    @SerializedName("sender_id")
    val senderID: Long,

    @SerializedName("conversation_id")
    val conversationID: Long,

    @SerializedName("seq_number")
    val seqNumber: Long
)