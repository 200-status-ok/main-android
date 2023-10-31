package com.example.haminjast.data


import com.example.haminjast.data.network.LoginRetrofit
import com.example.haminjast.data.network.LoginRetrofitService
import com.example.haminjast.data.network.OtpRequest
import com.example.haminjast.data.network.VerifyOtpRequest
import com.example.haminjast.data.network.VerifyOtpResponse

object LoginRepository{
    private val apiService: LoginRetrofitService =
        LoginRetrofit.getRetrofitInstance()
            .create(LoginRetrofitService::class.java)

    suspend fun sendOTP(userName: String) : Result<Unit> {
        val res = apiService.sendOtpRequest(OtpRequest(userName))
        return if (res.isSuccessful){
            Result.success(Unit)
        }else{
            Result.failure(Exception("OTP not sent"))
        }
    }

    suspend fun verifyOTP(userName: String, otp: String) : Result<VerifyOtpResponse?> {
        val res = apiService.verifyOtpRequest(VerifyOtpRequest(userName,otp))
        return if(res.isSuccessful){
            Result.success(res.body())
        }else{
            Result.failure(Exception("OTP not verified"))
        }
    }

}