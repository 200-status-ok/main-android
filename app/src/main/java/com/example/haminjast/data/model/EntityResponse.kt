package com.example.haminjast.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.haminjast.ui.model.Contact
import com.example.haminjast.ui.model.PosterStatus
import com.example.haminjast.ui.model.UiPoster
import com.google.gson.annotations.SerializedName

data class EntityResponse(
    val max_page: Int,
    val posters: List<Poster>,
    val total: Int
)

@Entity(tableName = "posters")
data class Poster(
    @SerializedName("addresses")
    val addresses: List<Address>,

    @SerializedName("alert")
    val alert: Boolean,

    @SerializedName("award")
    val award: Int,

    @SerializedName("chat")
    val chat: Boolean,

    @SerializedName("created_at")
    val created_at: String,

    @SerializedName("description")
    val description: String,

    @PrimaryKey
    val id: Int,

    @SerializedName("images")
    val images: List<String>,

    @SerializedName("special_type")
    val special_type: String,

    @SerializedName("state")
    val state: String,

    @SerializedName("status")
    val status: String,

    @SerializedName("tags")
    val tags: List<Tag>,

    @SerializedName("tel_id")
    val tel_id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("updated_at")
    val updated_at: String,

    @SerializedName("user_id")
    val user_id: Int,

    @SerializedName("user_phone")
    val user_phone: String
)

data class Address(
    val address_detail: String,
    val city: String,
    val location: Location,
    val province: String
)

data class Location(
    val lat: Double,
    val lon: Double
)

data class Tag(
    val id: Int,
    val name: String,
    val state: String
)

fun posterToUiPoster(poster: Poster): UiPoster {
    return UiPoster(
        id = poster.id,
        title = poster.title,
        description = poster.description,
        imageUrls = poster.images,
        timeCreatedTimeStamp = 0,
        status = if(poster.status == "lost") PosterStatus.Lost else PosterStatus.Found,
        vicinity = poster.addresses[0].address_detail,
        reward = poster.award.toLong(),
        lat = poster.addresses[0].location.lat,
        lng = poster.addresses[0].location.lon,
        issuerId = poster.user_id,
        contacts = listOf(
            Contact("شماره تماس", poster.user_phone),
            Contact("تلگرام", poster.tel_id)),
    )
}