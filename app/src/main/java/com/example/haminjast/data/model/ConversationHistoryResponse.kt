package com.example.haminjast.data.model


import com.google.gson.annotations.SerializedName

class ConversationHistoryResponse :
    ArrayList<ConversationHistoryResponse.ConversationHistoryResItem>() {
    data class ConversationHistoryResItem(
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("deleted_at")
        val deletedAt: DeletedAt,
        @SerializedName("id")
        val id: Int,
        @SerializedName("image_url")
        val imageUrl: String,
        @SerializedName("member_id")
        val peerID: Int,
        @SerializedName("messages")
        val messages: List<Message>,
        @SerializedName("name")
        val name: String,
        @SerializedName("owner_id")
        val ownerId: Int,
        @SerializedName("poster_id")
        val posterId: Int,
        @SerializedName("updated_at")
        val updatedAt: String
    ) {
        data class DeletedAt(
            @SerializedName("time")
            val time: String,
            @SerializedName("valid")
            val valid: Boolean
        )

        data class Message( //TODO remove, use MessageResponse instead
            @SerializedName("content")
            val content: String,
            @SerializedName("conversation_id")
            val conversationId: Int,
            @SerializedName("created_at")
            val createdAt: String,
            @SerializedName("deleted_at")
            val deletedAt: DeletedAt,
            @SerializedName("id")
            val id: Int,
            @SerializedName("is_send")
            val isSend: Boolean,
            @SerializedName("receiver_id")
            val receiverId: Int,
            @SerializedName("sender_id")
            val senderId: Int,
            @SerializedName("status")
            val status: String,
            @SerializedName("type")
            val type: String,
            @SerializedName("updated_at")
            val updatedAt: String
        ) {
            data class DeletedAt(
                @SerializedName("time")
                val time: String,
                @SerializedName("valid")
                val valid: Boolean
            )
        }
    }
}