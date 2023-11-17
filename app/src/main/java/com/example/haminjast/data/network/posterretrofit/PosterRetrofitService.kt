package com.example.haminjast.data.network.posterretrofit

import com.example.haminjast.data.model.EntityResponse
import com.example.haminjast.data.model.EntityResponseForPoster
import com.example.haminjast.data.model.RequestBodyForAddPoster
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PosterRetrofitService {
    @GET("posters")
    suspend fun getPosters(
        @Query("page_id") pageId: Int,
        @Query("page_size") pageSize: Int,
        @Query("sort") sort: String,
        @Query("sort_by") sortBy: String,
        @Query("status") status: String,
        @Query("state") state: String,
        @Query("special_type") specialType: String
    ): Response<EntityResponse>

    @GET("posters/{posterId}")
    suspend fun getPoster(
        @Path("posterId") posterId: Int
    ): Response<EntityResponseForPoster>


    @POST("/api/v1/posters/authorize")
    suspend fun createPoster(
        @Header("authorization") authorization: String,
        @Header("accept") acceptHeader: String,
        @Header("Content-Type") contentTypeHeader: String,
        @Body requestBody: RequestBodyForAddPoster
    ): Response<EntityResponseForPoster>
}