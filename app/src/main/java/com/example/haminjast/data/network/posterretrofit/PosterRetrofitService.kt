package com.example.haminjast.data.network.posterretrofit

import com.example.haminjast.data.model.CreatePosterRequest
import com.example.haminjast.data.model.CreatePosterResponse
import com.example.haminjast.data.model.EntityResponse
import com.example.haminjast.data.model.GetPosterByIdResponse
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
        @Query("search_phrase") searchPhrase: String = "",
        @Query("only_awards") onlyAwards: Boolean = false,
        @Query("status") status: String,
        @Query("state") state: String,
        @Query("lat") lat: Double?,
        @Query("lon") lon: Double?,
        @Query("special_type") specialType: String
    ): Response<EntityResponse>

    @GET("posters/{posterId}")
    suspend fun getPosterById(
        @Path("posterId") posterId: Int
    ): Response<GetPosterByIdResponse>


    @POST("/api/v1/posters/authorize")
    suspend fun createPoster(
        @Header("authorization") authorization: String,
        @Header("accept") acceptHeader: String,
        @Header("Content-Type") contentTypeHeader: String,
        @Body requestBody: CreatePosterRequest
    ): Response<CreatePosterResponse>
}