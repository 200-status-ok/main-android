package com.example.haminjast.data.repository


import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.haminjast.data.mediator.PosterRemoteMediator
import com.example.haminjast.data.model.CreatePosterRequest
import com.example.haminjast.data.model.GeneratePosterInfoResponse
import com.example.haminjast.data.model.GetPosterByIdResponse
import com.example.haminjast.data.model.Poster
import com.example.haminjast.data.model.UploadImageResponse
import com.example.haminjast.data.network.posterretrofit.PosterRetrofitService
import com.example.haminjast.ui.model.Contact
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import java.io.File

class PosterRepository( //TODO singleton
    private val apiService: PosterRetrofitService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
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

    fun getPosters(
        pageSize: Int,
        query: String,
        sort: String,
        sortBy: String,
        status: String,
        state: String,
        specialType: String,
        lat: Double?,
        lon: Double?,
        onlyAwards: Boolean
    ): Pager<Int, Poster> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PosterRemoteMediator(
                    apiService,
                    query = query,
                    sort = sort,
                    sortBy = sortBy,
                    status = status,
                    state = state,
                    specialType = specialType,
                    lat = lat,
                    lon = lon,
                    onlyAwards = onlyAwards
                )
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

    suspend fun generatePosterInfo(imageUrl: String): Result<GeneratePosterInfoResponse?> {
        return withContext(ioDispatcher) {
            try {
                val res = apiService.generatePosterInfo(imageUrl)
                if (res.isSuccessful) {
                    Result.success(res.body())
                } else {
                    Result.failure(Exception("No info generated"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun uploadImage(file:File): Result<UploadImageResponse?> {
        return withContext(ioDispatcher) {
            try {
                val filePart = MultipartBody.Part.createFormData("image", file.name, RequestBody.create(
                    "image/*".toMediaTypeOrNull(), file))

                val call = apiService.uploadAttachment(filePart)
                val res= call?.execute()
                res?: return@withContext Result.failure(Exception("No info generated"))
                if (res.isSuccessful) {
                    Log.d("modar","upload image success ${res.body()?.url}")
                    Result.success(res.body())
                } else {
                    Log.d("modar","{${res.code()} ${res.message()}} upload image failed}");
                    Result.failure(Exception("No info generated"))
                }
            } catch (e: Exception) {
                Log.d("modar","upload image failed ${e.message}");
                Result.failure(e)
            }
        }
    }
}