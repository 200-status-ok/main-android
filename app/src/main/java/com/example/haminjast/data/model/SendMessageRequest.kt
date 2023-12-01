package com.example.haminjast.data.model


import com.google.gson.annotations.SerializedName

data class SendMessageRequest( //TODO change based on doc
    @SerializedName("content")
    val content: String,
    @SerializedName("conversation_id")
    val conversationId: Int,
    @SerializedName("poster_id")
    val posterId: Int,
    @SerializedName("receiver_id")
    val receiverId: Int,
    @SerializedName("sender_id")
    val senderId: Int,
    @SerializedName("type")
    val type: String
)

//class SendMessageRequest( //TODO this is the right one
//    @SerializedName("conversation_id")
//    val conversationId: Long,
//
//    @SerializedName("content")
//    val content: String,
//
//    @SerializedName("content_type")
//    val contentType: String
//)