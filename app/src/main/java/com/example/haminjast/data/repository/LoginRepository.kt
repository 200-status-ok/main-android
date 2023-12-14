package com.example.haminjast.data.repository


import com.example.haminjast.User
import com.example.haminjast.data.model.VOR
import com.example.haminjast.data.network.loginretrofit.LoginRetrofitService
import com.example.haminjast.data.network.loginretrofit.OtpRequest
import com.example.haminjast.data.network.loginretrofit.VerifyOtpRequest
import com.example.haminjast.data.network.loginretrofit.VerifyOtpResponse

class LoginRepository constructor(private val apiService: LoginRetrofitService){ //TODO private constructor

    suspend fun sendOTP(userName: String) : Result<Unit> {
        val res = apiService.sendOtpRequest(OtpRequest(userName))
        return if (res.isSuccessful){
            Result.success(Unit)
        }else{
            Result.failure(Exception("OTP not sent"))
        }
    }

    suspend fun verifyOTP(userName: String, otp: String) : Result<VOR?> {
        val res = apiService.verifyOtpRequest(VerifyOtpRequest(userName, otp))
        return if (res.isSuccessful) {
            Result.success(res.body())
        } else {
            Result.failure(Exception("OTP not verified"))
        }
    }

    suspend fun loginUserWithGoogle(email: String) : Result<VerifyOtpResponse?> {
        val res = apiService.loginUserWithGoogle(email)
        return if(res.isSuccessful){
            Result.success(res.body())
        }else{
            Result.failure(Exception("OTP not verified"))
        }
    }

    suspend fun getUserId(token:String= User.token): Result<Int?> {
        val res = apiService.getUser(authorization = "Bearer $token")
        if (res.isSuccessful) {
            res.body()?.let {
                return Result.success(it.id)
            }
            return Result.failure(Exception("ID is null"))
        } else {
            return Result.failure(Exception("OTP not verified"))
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: LoginRepository? = null
        fun getInstance(apiService: LoginRetrofitService): LoginRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: LoginRepository(apiService)
                        .also { INSTANCE = it }
            }
    }
}