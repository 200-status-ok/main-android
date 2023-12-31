package com.example.haminjast.data.model


import com.google.gson.annotations.SerializedName

data class SendMessageRequest(
    @SerializedName("content")
    val content: String,
    @SerializedName("conversation_id")
    val conversationId: Int,
    @SerializedName("id")
    val id: Long,
    @SerializedName("poster_id")
    val posterId: Int,
    @SerializedName("type")
    val type: String
)
