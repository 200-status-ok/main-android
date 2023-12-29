package com.example.haminjast.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") val id: Int,
    @SerializedName("marked_posters") val markedPosters: List<MarkedPoster>,
    @SerializedName("posters") val posters: List<PosterU>,
    @SerializedName("username") val username: String,
    @SerializedName("wallet") val wallet: Int
)

data class MarkedPoster(
    @SerializedName("created_at") val createdAt: Long,
    @SerializedName("id") val id: Int,
    @SerializedName("poster") val poster: PosterU,
    @SerializedName("poster_id") val posterId: Int,
    @SerializedName("updated_at") val updatedAt: Long,
    @SerializedName("user_id") val userId: Int
)

data class PosterU(
    @SerializedName("addresses") val addresses: List<AddressP>,
    @SerializedName("award") val award: Int,
    @SerializedName("created_at") val createdAt: Long,
    @SerializedName("description") val description: String,
    @SerializedName("id") val id: Int,
    @SerializedName("images") val images: List<ImageP>,
    @SerializedName("special_type") val specialType: String,
    @SerializedName("state") val state: String,
    @SerializedName("status") val status: String,
    @SerializedName("tags") val tags: List<TagP>,
    @SerializedName("telegram_id") val telegramId: String,
    @SerializedName("title") val title: String,
    @SerializedName("updated_at") val updatedAt: Long,
    @SerializedName("user_id") val userId: Int,
    @SerializedName("user_phone") val userPhone: String
)

data class AddressP(
    @SerializedName("address_detail") val addressDetail: String,
    @SerializedName("city") val city: String,
    @SerializedName("created_at") val createdAt: Long,
    @SerializedName("id") val id: Int,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("poster_id") val posterId: Int,
    @SerializedName("province") val province: String,
    @SerializedName("updated_at") val updatedAt: Long
)

data class ImageP(
    @SerializedName("created_at") val createdAt: Long,
    @SerializedName("id") val id: Int,
    @SerializedName("updated_at") val updatedAt: Long,
    @SerializedName("url") val url: String
)

data class TagP(
    @SerializedName("created_at") val createdAt: Long,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("updated_at") val updatedAt: Long
)

data class MarkedPosterResponse(
    @SerializedName("message") val message: String
)