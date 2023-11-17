package com.example.haminjast.ui.model

import com.example.haminjast.R

data class UiPoster(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrls: List<String>? = null,
    val timeCreatedTimeStamp: Long,
    val status: PosterStatus,
    val vicinity: String,
    val reward: Long? = null,
    val lat: Double,
    val lng: Double,
    val issuerId: Int,
    val contacts: List<Contact>? = null
) {
    val timeCreated: String
        get() = "سه دقیقه پیش"
}

data class Contact(
    val name: String,
    val value: String
)

enum class PosterStatus(val value: Int) {
    Lost(R.string.lost), Found(R.string.found)
}