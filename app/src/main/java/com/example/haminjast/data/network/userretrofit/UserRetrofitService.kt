package com.example.haminjast.data.network.userretrofit

import com.example.haminjast.data.model.GetUserResponse
import com.example.haminjast.data.model.MarkedPosterResponse
import com.example.haminjast.data.model.User
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface UserRetrofitService {
    @GET("/api/v1/users/authorize")
    suspend fun getUser(
        @Header("authorization") authorization: String
    ): Response<User>

    @PATCH("/api/v1/users/authorize/mark-poster/{id}")
    suspend fun markPoster(
        @Header("authorization") authorization: String,
        @Path("id") id: Int
    ): Response<MarkedPosterResponse>

    @DELETE("/api/v1/users/authorize/mark-poster/{id}")
    suspend fun unmarkPoster(
        @Header("authorization") authorization: String,
        @Path("id") id: Int
    ): Response<MarkedPosterResponse>
}