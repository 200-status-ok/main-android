package com.example.haminjast.data.network.loginretrofit

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginRetrofitService{
    //send OTP
    @POST("users/auth/otp/send")
    @Headers("accept: application/json", "Content-Type: application/json")
    suspend fun sendOtpRequest(
        @Body otpRequest: OtpRequest
    ): Response<OtpResponse>
    //verify OTP
    @POST("users/auth/otp/login")
    @Headers("accept: application/json", "Content-Type: application/json")
    suspend fun verifyOtpRequest(
        @Body otpRequest: VerifyOtpRequest
    ): Response<VerifyOtpResponse>

    @GET("users/auth/google/login/android")
    @Headers("accept: application/json")
    suspend fun loginUserWithGoogle(
        @Query("email") email: String
    ): Response<VerifyOtpResponse>

}
data class OtpRequest(
    val username: String
)
data class OtpResponse(
    val message: String
)
data class VerifyOtpRequest(
    val username: String,
    val otp: String
)
data class VerifyOtpResponse(
    val message: String,
    val token: String
)