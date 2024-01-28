package com.example.haminjast.data.repository

import com.example.haminjast.User.token
import com.example.haminjast.data.model.CreatePosterRequest
import com.example.haminjast.data.model.CreatePosterResponse
import com.example.haminjast.data.model.GetPosterByIdResponse
import com.example.haminjast.data.network.posterretrofit.PosterRetrofitService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import retrofit2.Response

@ExperimentalCoroutinesApi
class PosterRepositoryTest {

    // This rule is used to initialize Mockito mocks
    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var apiService: PosterRetrofitService

    private lateinit var posterRepository: PosterRepository

    @Before
    fun setUp() {
        posterRepository = PosterRepository(apiService)
    }

    @Test
    fun `addPoster should return success when the API call is successful`() = runBlocking {
        // Arrange
        val expectedResult = Result.success("Poster added <pending for approval>")
        `when`(
            apiService.createPoster(
                authorization = "Bearer token",
                acceptHeader = "application/json",
                contentTypeHeader = "application/json",
                requestBody = CreatePosterRequest(
                    addresses = listOf(
                        CreatePosterRequest.Addresse(
                            addressDetail = "string",
                            city = "string",
                            latitude = 35.0.toInt(),
                            longitude = 35.0.toInt(),
                            province = "string"
                        )
                    ),
                    imgUrls = emptyList(),
                    poster = CreatePosterRequest.Poster(
                        alert = true, // todo get from ui
                        award = 0,
                        chat = true, // todo get from ui
                        description = "description",
                        specialType = "normal", // todo get from ui
                        state = "string", // todo this should be removed from backend
                        status = "status",
                        telId = "@durov", // todo
                        title = "title",
                        userPhone = "07295513344" // todo
                    ),
                    tags = emptyList()
                )
            )
        ).thenReturn(
            Response.success(
                CreatePosterResponse(
                    addresses = emptyList(),
                    award = 0,
                    createdAt = 0,
                    description = "description",
                    id = 0,
                    images = emptyList(),
                    specialType = "specialType",
                    state = "state",
                    status = "status",
                    tags = emptyList(),
                    telegramId = "telegramId",
                    title = "title",
                    updatedAt = "updatedAt",
                    userId = 0,
                    userPhone = "userPhone"
                )
            )
        )

        // Act
        val result = posterRepository.addPoster(
            "token", 35.0 to 35.0, emptyList(), "title", "description", emptyList(),
            "status", emptyList(), 0
        )

        // Assert
        assertEquals(expectedResult, result)
    }

    @Test
    fun `getPosterById should return success with UiPoster when the API call is successful`() =
        runBlocking {
            // Arrange
            val resEntity = GetPosterByIdResponse(
                addresses = emptyList(),
                award = 0,
                createdAt = 0,
                description = "description",
                id = 0,
                images = emptyList(),
                specialType = "specialType",
                state = "state",
                status = "status",
                tags = emptyList(),
                telegramId = "telegramId",
                title = "title",
                updatedAt = "updatedAt",
                userId = 0,
                userPhone = "userPhone"
            )

            val expectedResult = Result.success(resEntity)
            `when`(apiService.getPosterById(0))
                .thenReturn(
                    Response.success(
                        resEntity
                    )
                )

            // Act
            val result = posterRepository.getPosterById(0)

            // Assert
            assertEquals(expectedResult, result)
        }


}
