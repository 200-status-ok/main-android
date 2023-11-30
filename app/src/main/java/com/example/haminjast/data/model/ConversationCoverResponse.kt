package com.example.haminjast.data.model


import com.google.gson.annotations.SerializedName

class ConversationCoverResponse : ArrayList<ConversationCoverResponse.ConversationCoverResItem>() {
    data class ConversationCoverResItem private constructor(
        @SerializedName("id")
        val id: Int,
        @SerializedName("image_url")
        val imageUrl: String,
        @SerializedName("is_owner")
        val isOwner: Boolean,
        @SerializedName("name")
        val title: String,
        @SerializedName("poster_id")
        val posterId: Int,
//        @SerializedName("last_message")
//        val lastMessage: MessageResponse,
    )
}