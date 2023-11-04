package com.example.haminjast.data.network.posterretrofit

import com.example.haminjast.data.model.EntityResponse
import retrofit2.Response
import retrofit2.http.GET
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
}