package com.example.haminjast.data.repository

import android.util.Log
import com.example.haminjast.data.model.GetUserResponse
import com.example.haminjast.data.model.User
import com.example.haminjast.data.network.userretrofit.UserRetrofitService

class UserRepository(private val apiService: UserRetrofitService) {
    suspend fun getUser(token: String) :Result<User?>{
        val res = apiService.getUser(authorization = "Bearer $token")
        Log.d("mhmdrz" , "  $res")
        return if (res.isSuccessful) {
            Result.success(res.body())
        } else {
            Result.failure(Exception("OTP not verified"))
        }
    }

    suspend fun markPoster(token: String , id: Int) :Result<Unit>{
        val res = apiService.markPoster(authorization = "Bearer $token", id = id)
        return if (res.isSuccessful) {
            Result.success(Unit)
        } else {
            Result.failure(Exception("OTP not verified"))
        }
    }

    suspend fun unMarkPoster(token: String , id: Int) :Result<Unit>{
        val res = apiService.unmarkPoster(authorization = "Bearer $token",id = id)
        return if (res.isSuccessful) {
            Result.success(Unit)
        } else {
            Result.failure(Exception("OTP not verified"))
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserRepository? = null
        fun getInstance(apiService: UserRetrofitService): UserRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: UserRepository(apiService)
                        .also { INSTANCE = it }
            }
    }
}