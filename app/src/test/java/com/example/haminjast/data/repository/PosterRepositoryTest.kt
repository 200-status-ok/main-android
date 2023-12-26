//package com.example.haminjast.data.repository
//
//import com.example.haminjast.data.model.AddPoster
//import com.example.haminjast.data.model.AddressAddPoster
//import com.example.haminjast.data.model.EntityResponseForPoster
//import com.example.haminjast.data.model.RequestBodyForAddPoster
//import com.example.haminjast.data.model.toUiPoster
//import com.example.haminjast.data.network.posterretrofit.PosterRetrofitService
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.runBlocking
//import org.junit.Assert.assertEquals
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.mockito.Mock
//import org.mockito.Mockito.`when`
//import org.mockito.junit.MockitoJUnit
//import org.mockito.junit.MockitoRule
//import retrofit2.Response
//
//@ExperimentalCoroutinesApi
//class PosterRepositoryTest {
//
//    // This rule is used to initialize Mockito mocks
//    @get:Rule
//    var mockitoRule: MockitoRule = MockitoJUnit.rule()
//
//    @Mock
//    private lateinit var apiService: PosterRetrofitService
//
//    private lateinit var posterRepository: PosterRepository
//
//    @Before
//    fun setUp() {
//        posterRepository = PosterRepository(apiService)
//    }
//
//    @Test
//    fun `addPoster should return success when the API call is successful`() = runBlocking {
//        // Arrange
//        val expectedResult = Result.success("Poster added <pending for approval>")
//        `when`(
//            apiService.createPoster(
//                authorization = "Bearer token",
//                acceptHeader = "application/json",
//                contentTypeHeader = "application/json",
//                requestBody = RequestBodyForAddPoster(
//                    addresses = listOf(AddressAddPoster(
//                        address_detail = "Sttrsfg",
//                        city = "Sttrsfg",
//                        latitude = 32.1,
//                        longitude = 26.51,
//                        province = "Sttrsfg"
//                    )),
//                    img_urls = listOf( "normal"),
//                    poster = AddPoster(
//                        alert = false,
//                        award = 0,
//                        chat = true,
//                        description = "description",
//                        special_type = "normal",
//                        state = "pending",
//                        status = "lost",
//                        tel_id =  "normal",
//                        title =  "normal",
//                        user_phone = "normal"
//                    ),
//                    tags = listOf("normal")
//                )
//            )
//        ).thenReturn(
//            Response.success(
//                EntityResponseForPoster(
//                    id = 0,
//                    title = "",
//                    status = "",
//                    description = "",
//                    telegramId = "",
//                    userPhone = "",
//                    addresses = emptyList(),
//                    images = emptyList(),
//                    tags = emptyList(),
//                    userId = 0,
//                    award = 0,
//                    createdAt = "",
//                    updatedAt = "",
//                    state = "",
//                    specialType = ""
//                )
//            )
//        )
//
//        // Act
//        val result = posterRepository.addPoster(
//            "token", null, emptyList(), "title", "description", emptyList(),
//            "status", emptyList(), 0, true
//        )
//
//        // Assert
//        assertEquals(expectedResult, result)
//    }
//
//    @Test
//    fun `getPosterById should return success with UiPoster when the API call is successful`() =
//        runBlocking {
//            // Arrange
//            val resEntity = EntityResponseForPoster(
//                id = 0,
//                title = "",
//                status = "",
//                description = "",
//                telegramId = "",
//                userPhone = "",
//                addresses = emptyList(),
//                images = emptyList(),
//                tags = emptyList(),
//                userId = 0,
//                award = 0,
//                createdAt = "",
//                updatedAt = "",
//                state = "",
//                specialType = ""
//            )
//
//            val expectedResult = Result.success(resEntity.toUiPoster())
//            `when`(apiService.getPosterById(0))
//                .thenReturn(
//                    Response.success(
//                        resEntity
//                    )
//                )
//
//            // Act
//            val result = posterRepository.getPosterById(0)
//
//            // Assert
//            assertEquals(expectedResult, result)
//        }
//
//
//}
