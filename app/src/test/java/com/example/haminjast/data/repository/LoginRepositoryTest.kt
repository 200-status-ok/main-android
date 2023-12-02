package com.example.haminjast.data.repository

import com.example.haminjast.data.network.loginretrofit.LoginRetrofitService
import com.example.haminjast.data.network.loginretrofit.OtpRequest
import com.example.haminjast.data.network.loginretrofit.OtpResponse
import com.example.haminjast.data.network.loginretrofit.VerifyOtpRequest
import com.example.haminjast.data.network.loginretrofit.VerifyOtpResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import retrofit2.Response

class LoginRepositoryTest {


    // This rule is used to initialize Mockito mocks
    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var apiService: LoginRetrofitService

    private lateinit var loginRepository: LoginRepository

    @Before
    fun setUp() {
        loginRepository = LoginRepository(apiService)
    }

    @Test
    fun `sendOTP should return success when API call is successful`() = runBlocking {
        // Arrange
        val userName = "testUser"
        val expectedResponse = Response.success(OtpResponse("OTP sent"))
        Mockito.`when`(apiService.sendOtpRequest(OtpRequest(userName))).thenReturn(expectedResponse)

        // Act
        val result = loginRepository.sendOTP(userName)

        // Assert
        assertEquals(Result.success(Unit), result)
    }

    @Test
    fun `verifyOTP should return success when API call is successful`() = runBlocking {
        // Arrange
        val userName = "testUser"
        val otp = "123456"
        val expectedResponse = Response.success(VerifyOtpResponse("OTP verified", "token"))
//        'when'(apiService.verifyOtpRequest(VerifyOtpRequest(userName, otp))).thenReturn(
//            expectedResponse
//        )

        Mockito.`when`(apiService.verifyOtpRequest(VerifyOtpRequest(userName, otp))
        ).thenReturn(
            expectedResponse
        )


        // Act
        val result = loginRepository.verifyOTP(userName, otp)

        // Assert
        assertEquals(Result.success(expectedResponse.body()), result)
    }

    @Test
    fun `loginUserWithGoogle should return success when API call is successful`() = runBlocking {
        // Arrange
        val email = "test@example.com"
        val expectedResponse = Response.success(VerifyOtpResponse("OTP verified", "token"))
        Mockito.`when`(apiService.loginUserWithGoogle(email)).thenReturn(expectedResponse)

        // Act
        val result = loginRepository.loginUserWithGoogle(email)

        // Assert
        assertEquals(Result.success(expectedResponse.body()), result)
    }
}
