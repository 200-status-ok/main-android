package com.example.haminjast.data.model


import com.google.gson.annotations.SerializedName

data class CreatePosterResponse(
    @SerializedName("addresses")
    val addresses: List<Addresse>,
    @SerializedName("award")
    val award: Int,
    @SerializedName("created_at")
    val createdAt: Long,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("special_type")
    val specialType: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("tags")
    val tags: List<Tag>,
    @SerializedName("telegram_id")
    val telegramId: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("user_phone")
    val userPhone: String
) {
    data class Addresse(
        @SerializedName("address_detail")
        val addressDetail: String,
        @SerializedName("address_id")
        val addressId: Int,
        @SerializedName("city")
        val city: String,
        @SerializedName("created_at")
        val createdAt: Long,
        @SerializedName("deleted_at")
        val deletedAt: DeletedAt,
        @SerializedName("id")
        val id: Int,
        @SerializedName("latitude")
        val latitude: Int,
        @SerializedName("longitude")
        val longitude: Int,
        @SerializedName("province")
        val province: String,
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

    data class Image(
        @SerializedName("created_at")
        val createdAt: Long,
        @SerializedName("deleted_at")
        val deletedAt: DeletedAt,
        @SerializedName("id")
        val id: Int,
        @SerializedName("poster_id")
        val posterId: Int,
        @SerializedName("updated_at")
        val updatedAt: String,
        @SerializedName("url")
        val url: String
    ) {
        data class DeletedAt(
            @SerializedName("time")
            val time: String,
            @SerializedName("valid")
            val valid: Boolean
        )
    }

    data class Tag(
        @SerializedName("created_at")
        val createdAt: Long,
        @SerializedName("deleted_at")
        val deletedAt: DeletedAt,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("posters")
        val posters: List<Poster>,
        @SerializedName("state")
        val state: String,
        @SerializedName("updated_at")
        val updatedAt: String
    ) {
        data class DeletedAt(
            @SerializedName("time")
            val time: String,
            @SerializedName("valid")
            val valid: Boolean
        )

        data class Poster(
            @SerializedName("address")
            val address: List<Addres>,
            @SerializedName("award")
            val award: Int,
            @SerializedName("created_at")
            val createdAt: Long,
            @SerializedName("deleted_at")
            val deletedAt: DeletedAt,
            @SerializedName("description;")
            val description: String,
            @SerializedName("has_alert")
            val hasAlert: Boolean,
            @SerializedName("has_chat")
            val hasChat: Boolean,
            @SerializedName("id")
            val id: Int,
            @SerializedName("image")
            val image: List<Image>,
            @SerializedName("special_type")
            val specialType: String,
            @SerializedName("state")
            val state: String,
            @SerializedName("status")
            val status: String,
            @SerializedName("tags")
            val tags: List<String>,
            @SerializedName("telegram_id")
            val telegramId: String,
            @SerializedName("title")
            val title: String,
            @SerializedName("updated_at")
            val updatedAt: String,
            @SerializedName("user_id")
            val userId: Int,
            @SerializedName("user_phone")
            val userPhone: String
        ) {
            data class Addres(
                @SerializedName("address_detail")
                val addressDetail: String,
                @SerializedName("address_id")
                val addressId: Int,
                @SerializedName("city")
                val city: String,
                @SerializedName("created_at")
                val createdAt: Long,
                @SerializedName("deleted_at")
                val deletedAt: DeletedAt,
                @SerializedName("id")
                val id: Int,
                @SerializedName("latitude")
                val latitude: Int,
                @SerializedName("longitude")
                val longitude: Int,
                @SerializedName("province")
                val province: String,
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

            data class DeletedAt(
                @SerializedName("time")
                val time: String,
                @SerializedName("valid")
                val valid: Boolean
            )

            data class Image(
                @SerializedName("created_at")
                val createdAt: Long,
                @SerializedName("deleted_at")
                val deletedAt: DeletedAt,
                @SerializedName("id")
                val id: Int,
                @SerializedName("poster_id")
                val posterId: Int,
                @SerializedName("updated_at")
                val updatedAt: String,
                @SerializedName("url")
                val url: String
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
}