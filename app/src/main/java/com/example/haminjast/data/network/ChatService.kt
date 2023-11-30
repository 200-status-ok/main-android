package com.example.haminjast.data.network

import com.example.haminjast.data.model.ConversationCoverResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
interface ChatService{
    @POST("users/auth/otp/send")
    @Headers("accept: application/json", "Content-Type: application/json")
    suspend fun sendOtpRequest(
        @Body otpRequest: OtpRequest
    ): Response<OtpResponse>

    @POST("users/auth/otp/login")
    @Headers("accept: application/json", "Content-Type: application/json")
    suspend fun verifyOtpRequest(
        @Body otpRequest: VerifyOtpRequest
    ): Response<VerifyOtpResponse>

    @GET("chat/authorize/conversation")
    @Headers("accept: application/json", "Content-Type: application/json")
    suspend fun getConversations(
        @Header("authorization") authorization: String,
    ): Response<ConversationCoverResponse>
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