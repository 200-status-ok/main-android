package com.example.haminjast.data.model


import com.google.gson.annotations.SerializedName

data class CreatePosterRequest(
    @SerializedName("addresses")
    val addresses: List<Addresse>,
    @SerializedName("img_urls")
    val imgUrls: List<String>,
    @SerializedName("poster")
    val poster: Poster,
    @SerializedName("tags")
    val tags: List<String>
) {
    data class Addresse(
        @SerializedName("address_detail")
        val addressDetail: String,
        @SerializedName("city")
        val city: String,
        @SerializedName("latitude")
        val latitude: Int,
        @SerializedName("longitude")
        val longitude: Int,
        @SerializedName("province")
        val province: String
    )

    data class Poster(
        @SerializedName("alert")
        val alert: Boolean,
        @SerializedName("award")
        val award: Int,
        @SerializedName("chat")
        val chat: Boolean,
        @SerializedName("description")
        val description: String,
        @SerializedName("special_type")
        val specialType: String,
        @SerializedName("state")
        val state: String,
        @SerializedName("status")
        val status: String,
        @SerializedName("tel_id")
        val telId: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("user_phone")
        val userPhone: String
    )
}