package com.example.haminjast.data.network

import com.example.haminjast.data.model.ConversationCoverResponse
import com.example.haminjast.data.model.ConversationHistoryResponse
import com.example.haminjast.data.model.ReadMessageRequest
import com.example.haminjast.data.model.ReadMessageResponse
import com.example.haminjast.data.model.SendMessageResponse
import com.example.haminjast.data.model.SendMessageRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ChatService {
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

    @GET("chat/authorize/history/{conversation_id}")
    @Headers("accept: application/json", "Content-Type: application/json")
    suspend fun getConversationHistory(
        @Header("authorization") authorization: String,
        @Path("conversation_id") conversationID: String,
        @Query("page_id") pageID: Int,
        @Query("page_size") pageSize: Int,
    ): Response<ConversationHistoryResponse>

    @POST("chat/authorize/message")
    suspend fun sendMessage(
        @Header("authorization") authorization: String,
        @Header("accept") acceptHeader: String,
        @Header("Content-Type") contentTypeHeader: String,
        @Body sendMessageRequest: SendMessageRequest,
    ): Response<SendMessageResponse> //TODO change to SendMessageResponse

    @POST("chat/authorize/read")
    suspend fun readMessage(
        @Header("authorization") authorization: String,
        @Header("accept") acceptHeader: String,
        @Header("Content-Type") contentTypeHeader: String,
        @Body readMessageRequest: ReadMessageRequest,
    ): Response<ReadMessageResponse> //TODO change to SendMessageResponse

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