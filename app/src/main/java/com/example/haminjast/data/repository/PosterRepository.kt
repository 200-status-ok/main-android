package com.example.haminjast.data.repository


import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.haminjast.data.mediator.PosterRemoteMediator
import com.example.haminjast.data.model.AddPoster
import com.example.haminjast.data.model.AddressAddPoster
import com.example.haminjast.data.model.Poster
import com.example.haminjast.data.model.RequestBodyForAddPoster
import com.example.haminjast.data.model.toUiPoster
import com.example.haminjast.data.network.posterretrofit.PosterRetrofitService
import com.example.haminjast.ui.model.Contact
import com.example.haminjast.ui.model.UiPoster

class PosterRepository( //TODO singleton
    private val apiService : PosterRetrofitService
) {
    suspend fun addPoster(
        token : String,
        address : AddressAddPoster?,
        imageUrls : List<String>,
        title : String,
        description : String,
        contacts : List<Contact>,
        status : String,
        tags : List<String>,
        award : Int,
        haveChat : Boolean
        ): Result<String> {
        return try {
            val res = apiService.createPoster(
                authorization = "Bearer $token",
                acceptHeader = "application/json",
                contentTypeHeader = "application/json",
                requestBody = RequestBodyForAddPoster(
                    addresses = listOf(AddressAddPoster(
                        address_detail = "Sttrsfg",
                        city = "Sttrsfg",
                        latitude = 32.1,
                        longitude = 26.51,
                        province = "Sttrsfg"
                    )),
                    img_urls = listOf( "normal"),
                    poster = AddPoster(
                        alert = false,
                        award = 0,
                        chat = true,
                        description = "description",
                        special_type = "normal",
                        state = "pending",
                        status = "lost",
                        tel_id =  "normal",
                        title =  "normal",
                        user_phone = "normal"
                    ),
                    tags = listOf("normal")
                    )
                )
            Log.d("dafsd", "addPoster: ${res.errorBody()})")
            if (res.isSuccessful) {
                Result.success("Poster added <pending for approval>")
            } else {
                Result.failure(Exception("Poster not added"))
            }
        } catch (e: Exception) {
            Result.failure(e)
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
    suspend fun getPosterById(id: Int): Result<UiPoster?> {
        return try {
            val res = apiService.getPoster(id)
            if (res.isSuccessful) {
                val poster = res.body()
                val uiPoster = poster?.toUiPoster()
                Result.success(uiPoster)
            } else {
                Result.failure(Exception("Poster not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}