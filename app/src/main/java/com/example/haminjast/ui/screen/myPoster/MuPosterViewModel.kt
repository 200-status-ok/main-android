package com.example.haminjast.ui.screen.myPoster

import androidx.lifecycle.ViewModel
import com.example.haminjast.data.repository.PosterRepository
import com.example.haminjast.ui.model.PosterStatus
import com.example.haminjast.ui.model.UiPoster

class MyPosterViewModel(
    posterRepository: PosterRepository
): ViewModel() {


    val myPosters = listOf(
        UiPoster(
            id = 1,
            title = "کفش",
            description = "کفش پیدا شده",
            imageUrls = listOf("https://upload.wikimedia.org/wikipedia/commons/thumb/2/25/HP_Pavilion_zv6000_series.jpg/440px-HP_Pavilion_zv6000_series.jpg"),
            timeCreatedTimeStamp = 1629475200000,
            status = PosterStatus.Found,
            vicinity = "تهران",
            reward = 0,
            lat = 0.0,
            lng = 0.0,
            issuerId = 1,
            contacts = null
        ),
        UiPoster(
            id = 1,
            title = "کفش",
            description = "کفش پیدا شده",
            imageUrls = listOf("https://upload.wikimedia.org/wikipedia/commons/thumb/2/25/HP_Pavilion_zv6000_series.jpg/440px-HP_Pavilion_zv6000_series.jpg"),
            timeCreatedTimeStamp = 1629475200000,
            status = PosterStatus.Found,
            vicinity = "تهران",
            reward = 0,
            lat = 0.0,
            lng = 0.0,
            issuerId = 1,
            contacts = null
        ),
        UiPoster(
            id = 1,
            title = "کفش",
            description = "کفش پیدا شده",
            imageUrls = listOf("https://upload.wikimedia.org/wikipedia/commons/thumb/2/25/HP_Pavilion_zv6000_series.jpg/440px-HP_Pavilion_zv6000_series.jpg"),
            timeCreatedTimeStamp = 1629475200000,
            status = PosterStatus.Found,
            vicinity = "تهران",
            reward = 0,
            lat = 0.0,
            lng = 0.0,
            issuerId = 1,
            contacts = null
        ),
        UiPoster(
            id = 1,
            title = "کفش",
            description = "کفش پیدا شده",
            imageUrls = listOf("https://upload.wikimedia.org/wikipedia/commons/thumb/2/25/HP_Pavilion_zv6000_series.jpg/440px-HP_Pavilion_zv6000_series.jpg"),
            timeCreatedTimeStamp = 1629475200000,
            status = PosterStatus.Found,
            vicinity = "تهران",
            reward = 0,
            lat = 0.0,
            lng = 0.0,
            issuerId = 1,
            contacts = null
        ),
        UiPoster(
            id = 1,
            title = "کفش",
            description = "کفش پیدا شده",
            imageUrls = listOf("https://upload.wikimedia.org/wikipedia/commons/thumb/2/25/HP_Pavilion_zv6000_series.jpg/440px-HP_Pavilion_zv6000_series.jpg"),
            timeCreatedTimeStamp = 1629475200000,
            status = PosterStatus.Found,
            vicinity = "تهران",
            reward = 0,
            lat = 0.0,
            lng = 0.0,
            issuerId = 1,
            contacts = null
        ),
    )
}