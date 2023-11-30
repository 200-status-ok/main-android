package com.example.haminjast.data.model

import com.google.gson.annotations.SerializedName

class ReadMessageRequest(
    @SerializedName("message_ids")
    val messageIDs: List<Long>
)