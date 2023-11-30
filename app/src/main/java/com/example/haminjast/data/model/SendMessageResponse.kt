package com.example.haminjast.data.model

import com.google.gson.annotations.SerializedName

class SendMessageResponse(
    @SerializedName("message_id")
    val messageID:Long,

    @SerializedName("sender_id")
    val date:Long,

    @SerializedName("conversation_id")
    val conversationID:Long
)