package com.example.haminjast.ui.model

import android.util.Log
import com.example.haminjast.R
import com.example.haminjast.data.model.GetPosterByIdResponse

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

    companion object {
        fun fromResponse(response: GetPosterByIdResponse): UiPoster {
            Log.d("modar","images: ${response.images}");
            return UiPoster(
                id = response.id,
                title = response.title,
                description = response.description,
                imageUrls = if (response.images.isEmpty()) null else response.images.map { it.url },
                timeCreatedTimeStamp = 0, //todo
                status = if (response.status == "lost") PosterStatus.Lost else PosterStatus.Found,
                vicinity = response.addresses[0].addressDetail,
                reward = response.award.toLong(),
                lat = response.addresses[0].latitude.toDouble(),
                lng = response.addresses[0].longitude.toDouble(),
                issuerId = response.userId,
                contacts = listOf(
                    Contact(
                        name = "تلفن تماس",
                        value = response.userPhone
                    ),
                    Contact(
                        name = "تلگرام",
                        value = response.telegramId
                    )
                )
            )
        }
    }
}


data class Contact(
    val name: String,
    val value: String
)

enum class PosterStatus(val value: Int) {
    Lost(R.string.lost), Found(R.string.found)
}