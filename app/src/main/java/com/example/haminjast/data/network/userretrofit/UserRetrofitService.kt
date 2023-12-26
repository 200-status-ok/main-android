package com.example.haminjast.data.network.userretrofit

import com.example.haminjast.data.model.GetUserResponse
import com.example.haminjast.data.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserRetrofitService {
    @GET("/api/v1/users/authorize")
    suspend fun getUser(
        @Header("authorization") authorization: String
    ): Response<User>
}