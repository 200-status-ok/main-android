package com.example.haminjast.data.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.haminjast.data.mediator.PosterRemoteMediator
import com.example.haminjast.data.model.CreatePosterRequest
import com.example.haminjast.data.model.GetPosterByIdResponse
import com.example.haminjast.data.model.Poster
import com.example.haminjast.data.model.toUiPoster
import com.example.haminjast.data.network.posterretrofit.PosterRetrofitService
import com.example.haminjast.ui.model.Contact
import com.example.haminjast.ui.model.UiPoster

class PosterRepository( //TODO singleton
    private val apiService: PosterRetrofitService
) {
    suspend fun addPoster(
        token: String,
        latLong: Pair<Double, Double>,
        imageUrls: List<String>,
        title: String,
        description: String,
        contacts: List<Contact>,
        status: String,
        tags: List<String>,
        award: Int,
    ): Result<String> {
        val res = apiService.createPoster(
            authorization = "Bearer $token",
            acceptHeader = "application/json",
            contentTypeHeader = "application/json",
            requestBody = CreatePosterRequest(
                addresses = listOf(
                    CreatePosterRequest.Addresse(
                        addressDetail = "string",
                        city = "string",
                        latitude = latLong.first.toInt(),
                        longitude = latLong.second.toInt(),
                        province = "string"
                    )
                ),
                imgUrls = imageUrls,
                poster = CreatePosterRequest.Poster(
                    alert = true, // todo get from ui
                    award = award,
                    chat = true, // todo get from ui
                    description = description,
                    specialType = "normal", // todo get from ui
                    state = "string", // todo this should be removed from backend
                    status = status,
                    telId = "@durov", // todo
                    title = title,
                    userPhone = "07295513344" // todo
                ),
                tags = tags
            )
        )

        return if (res.isSuccessful) {
            Result.success("Poster added <pending for approval>")
        } else {
            Result.failure(Exception("Poster not added"))
        }
    }

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

    suspend fun getPosterById(id: Int): Result<GetPosterByIdResponse?> {
        return try {
            val res = apiService.getPosterById(id)
            if (res.isSuccessful) {
                Result.success(res.body())
            } else {
                Result.failure(Exception("Poster not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}