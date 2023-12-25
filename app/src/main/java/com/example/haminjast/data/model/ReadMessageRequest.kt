package com.example.haminjast.data.model


import com.google.gson.annotations.SerializedName

data class ReadMessageRequest(
    @SerializedName("message_ids")
    val messageIds: List<Long>,
    @SerializedName("sender_id")
    val senderId: Int
)