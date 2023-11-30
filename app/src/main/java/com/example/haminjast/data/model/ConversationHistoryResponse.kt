package com.example.haminjast.data.model

import com.google.gson.annotations.SerializedName

data class ConversationHistoryResponse(
    @SerializedName("peer_id")
    val peerID:Long,

    @SerializedName("messages")
    val messages:List<MessageResponse>
)