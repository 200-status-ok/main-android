package com.example.haminjast.data.model

import com.google.gson.annotations.SerializedName

class SendMessageRequest(
    @SerializedName("conversation_id")
    val conversationId: Long,

    @SerializedName("content")
    val content: String,

    @SerializedName("content_type")
    val contentType: String
)