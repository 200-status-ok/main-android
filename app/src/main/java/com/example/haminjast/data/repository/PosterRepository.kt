package com.example.haminjast.data.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.haminjast.data.mediator.PosterRemoteMediator
import com.example.haminjast.data.model.Poster
import com.example.haminjast.data.network.posterretrofit.PosterRetrofitService
import com.example.haminjast.ui.model.Contact
import com.example.haminjast.ui.model.PosterStatus.Lost
import com.example.haminjast.ui.model.UiPoster
import kotlinx.coroutines.delay

class PosterRepository(
    private val apiService : PosterRetrofitService
) {

    fun getPosters(pageSize: Int): Pager<Int, Poster> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PosterRemoteMediator(apiService)
            }
        )
    }
    suspend fun getPosterById(id: Int): Result<UiPoster?> {
        delay(100)
        return Result.success(
            UiPoster(
                id = 1,
                title = "کیف پول قهوه ای",
                description = "یک کیف پول قهوه ای در محدوده ی ستارخان گم شده. از یابنده تقاضا می شود که با شماره ی زیر تماس بگیرد.یک کیف پول قهوه ای در محدوده ی ستارخان گم شده.یک کیف پول قهوه ای در محدوده ی ستارخان گم شده. از یابنده تقاضا می شود که با شماره ی زیر تماس بگیرد.یک کیف پول قهوه ای در محدوده ی ستارخان گم شده.یک کیف پول قهوه ای در محدوده ی ستارخان گم شده.\n به یابنده مژدگانی داده خواهد شد. ",
                imageUrls = listOf(
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3c/WalletMpegMan.jpg/500px-WalletMpegMan.jpg",
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3c/WalletMpegMan.jpg/500px-WalletMpegMan.jpg",
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3c/WalletMpegMan.jpg/500px-WalletMpegMan.jpg"
                ),
                timeCreatedTimeStamp = 1698867077,
                status = Lost,
                vicinity = "ستارخان",
                reward = 200000,
                lat = 34.0,
                lng = 34.0,
                issuerId = 1,
                contacts = listOf(
                    Contact("شماره تماس", "09123456789"),
                    Contact("ایمیل", "felanihastam@gmail.com")
                )
            )
        )
    }
}