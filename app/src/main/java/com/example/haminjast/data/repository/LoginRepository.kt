package com.example.haminjast.data.repository


import com.example.haminjast.data.network.loginretrofit.LoginRetrofit
import com.example.haminjast.data.network.loginretrofit.LoginRetrofitService
import com.example.haminjast.data.network.loginretrofit.OtpRequest
import com.example.haminjast.data.network.loginretrofit.VerifyOtpRequest
import com.example.haminjast.data.network.loginretrofit.VerifyOtpResponse

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

    suspend fun loginUserWithGoogle(email: String) : Result<VerifyOtpResponse?> {
        val res = apiService.loginUserWithGoogle(email)
        return if(res.isSuccessful){
            Result.success(res.body())
        }else{
            Result.failure(Exception("OTP not verified"))
        }
    }

}