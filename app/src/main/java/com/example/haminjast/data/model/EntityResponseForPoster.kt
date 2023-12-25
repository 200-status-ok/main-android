package com.example.haminjast.data.model

import com.example.haminjast.ui.model.Contact
import com.example.haminjast.ui.model.PosterStatus
import com.example.haminjast.ui.model.UiPoster
import com.google.gson.annotations.SerializedName

data class Address1Poster(
    val id: Int,
    @SerializedName("created_at")
    val createdAt: Long,
    @SerializedName("updated_at")
    val updatedAt: String,
    val deletedAt: Long?,
    val province: String,
    val city: String,
    @SerializedName("address_detail")
    val addressDetail: String,
    val latitude: Double,
    val longitude: Double,
    @SerializedName("address_id")
    val addressId: Int
)

data class Image(
    val id: Int,
    @SerializedName("created_at")
    val createdAt: Long,
    @SerializedName("updated_at")
    val updatedAt: String,
    val deletedAt: Long?,
    val url: String,
    @SerializedName("poster_id")
    val posterId: Int
)

data class Tag1Poster(
    val id: Int,
    @SerializedName("created_at")
    val createdAt: Long,
    @SerializedName("updated_at")
    val updatedAt: String,
    val deletedAt: Long?,
    val name: String,
    val state: String,
    val posters: Any? // Change the type accordingly if you have information about the type.
)

data class EntityResponseForPoster(
    val id: Int,
    val title: String,
    val status: String,
    val description: String,
    @SerializedName("telegram_id")
    val telegramId: String,
    @SerializedName("user_phone")
    val userPhone: String,
    val addresses: List<Address1Poster>,
    val images: List<Image>,
    val tags: List<Tag1Poster>,
    @SerializedName("user_id")
    val userId: Int,
    val award: Int,
    @SerializedName("created_at")
    val createdAt: Long,
    @SerializedName("updated_at")
    val updatedAt: String,
    val state: String,
    @SerializedName("special_type")
    val specialType: String
)

fun EntityResponseForPoster.toUiPoster(): UiPoster {
    return UiPoster(
        id = id,
        title = title,
        description = description,
        imageUrls = images.map { it.url },
        timeCreatedTimeStamp = 0L,
        status = if (status == "lost") PosterStatus.Lost else PosterStatus.Found,
        vicinity = addresses.firstOrNull()?.province?:"",
        reward = award.toLong(),
        lat = addresses.firstOrNull()?.latitude ?: 0.0,
        lng = addresses.firstOrNull()?.longitude ?: 0.0,
        issuerId = userId,
        contacts = listOf(
            Contact("شماره تماس", userPhone),
            Contact("تلگرام", telegramId)
        ),
    )
}
