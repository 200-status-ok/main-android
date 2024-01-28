package com.example.haminjast.data.model

import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test

class GetUserResponseTest {

    @Test
    fun getUserResponseTest() {
        // Create test data
        val markedPosters = listOf(
            GetUserResponse.MarkedPoster(
                123,
                GetUserResponse.MarkedPoster.DeletedAt("2023-01-01", true),
                456,
                GetUserResponse.MarkedPoster.Poster(
                    listOf(
                        GetUserResponse.MarkedPoster.Poster.Addres(
                            "Detail",
                            789,
                            "City",
                            1000000,
                            GetUserResponse.MarkedPoster.Poster.Addres.DeletedAt("2023-01-01", true),
                            101,
                            2000000,
                            3000000,
                            "Province",
                            "2023-01-01"
                        )
                    ),
                    100,
                    2000000,
                    GetUserResponse.MarkedPoster.Poster.DeletedAt("2023-01-01", true),
                    "Description",
                    true,
                    false,
                    300,
                    listOf(
                        GetUserResponse.MarkedPoster.Poster.Image(
                            4000000,
                            GetUserResponse.MarkedPoster.Poster.Image.DeletedAt("2023-01-01", true),
                            500,
                            6000000,
                            "2023-01-01",
                            "https://example.com/image.jpg"
                        )
                    ),
                    "SpecialType",
                    "State",
                    "Status",
                    listOf(
                        GetUserResponse.MarkedPoster.Poster.Tag(
                            7000000,
                            GetUserResponse.MarkedPoster.Poster.Tag.DeletedAt("2023-01-01", true),
                            800,
                            "TagName",
                            listOf("Poster1", "Poster2"),
                            "State",
                            "2023-01-01"
                        )
                    ),
                    "TelegramId",
                    "Title",
                    "2023-01-01",
                    900,
                    "UserPhone"
                ),
                7000000,
                "2023-01-01",
                123
            )
        )

        val posters = listOf(
            GetUserResponse.Poster(
                listOf(
                    GetUserResponse.Poster.Addres(
                        "Detail",
                        789,
                        "City",
                        1000000,
                        GetUserResponse.Poster.Addres.DeletedAt("2023-01-01", true),
                        101,
                        2000000,
                        3000000,
                        "Province",
                        "2023-01-01"
                    )
                ),
                100,
                2000000,
                GetUserResponse.Poster.DeletedAt("2023-01-01", true),
                "Description",
                true,
                false,
                300,
                listOf(
                    GetUserResponse.Poster.Image(
                        4000000,
                        GetUserResponse.Poster.Image.DeletedAt("2023-01-01", true),
                        500,
                        6000000,
                        "2023-01-01",
                        "https://example.com/image.jpg"
                    )
                ),
                "SpecialType",
                "State",
                "Status",
                listOf(
                    GetUserResponse.Poster.Tag(
                        7000000,
                        GetUserResponse.Poster.Tag.DeletedAt("2023-01-01", true),
                        800,
                        "TagName",
                        listOf("Poster1", "Poster2"),
                        "State",
                        "2023-01-01"
                    )
                ),
                "TelegramId",
                "Title",
                "2023-01-01",
                900,
                "UserPhone"
            )
        )

        // Create a GetUserResponse instance for testing
        val getUserResponse = GetUserResponse(123, markedPosters, posters, "TestUser", 1000)

        // Verify the values
        assertEquals(123, getUserResponse.id)
        assertEquals(markedPosters, getUserResponse.markedPosters)
        assertEquals(posters, getUserResponse.posters)
        assertEquals("TestUser", getUserResponse.username)
        assertEquals(1000, getUserResponse.wallet)
    }

    // If you want to test JSON deserialization, you can use Mockito to mock the deserialization process
    @Test
    fun jsonDeserializationTest() {
        val json = """
            {
                "id": 123,
                "marked_posters": [
                    {
                        "created_at": 123,
                        "deleted_at": {
                            "time": "2023-01-01",
                            "valid": true
                        },
                        "id": 456,
                        "poster": {
                            "address": [
                                {
                                    "address_detail": "Detail",
                                    "address_id": 789,
                                    "city": "City",
                                    "created_at": 1000000,
                                    "deleted_at": {
                                        "time": "2023-01-01",
                                        "valid": true
                                    },
                                    "id": 101,
                                    "latitude": 2000000,
                                    "longitude": 3000000,
                                    "province": "Province",
                                    "updated_at": "2023-01-01"
                                }
                            ],
                            "award": 100,
                            "created_at": 2000000,
                            "deleted_at": {
                                "time": "2023-01-01",
                                "valid": true
                            },
                            "description;": "Description",
                            "has_alert": true,
                            "has_chat": false,
                            "id": 300,
                            "image": [
                                {
                                    "created_at": 4000000,
                                    "deleted_at": {
                                        "time": "2023-01-01",
                                        "valid": true
                                    },
                                    "id": 500,
                                    "poster_id": 6000000,
                                    "updated_at": "2023-01-01",
                                    "url": "https://example.com/image.jpg"
                                }
                            ],
                            "special_type": "SpecialType",
                            "state": "State",
                            "status": "Status",
                            "tags": [
                                {
                                    "created_at": 7000000,
                                    "deleted_at": {
                                        "time": "2023-01-01",
                                        "valid": true
                                    },
                                    "id": 800,
                                    "name": "TagName",
                                    "posters": ["Poster1", "Poster2"],
                                    "state": "State",
                                    "updated_at": "2023-01-01"
                                }
                            ],
                            "telegram_id": "TelegramId",
                            "title": "Title",
                            "updated_at": "2023-01-01",
                            "user_id": 900,
                            "user_phone": "UserPhone"
                        },
                        "poster_id": 7000000,
                        "updated_at": "2023-01-01",
                        "user_id": 123
                    }
                ],
                "posters": [
                    {
                        "address": [
                            {
                                "address_detail": "Detail",
                                "address_id": 789,
                                "city": "City",
                                "created_at": 1000000,
                                "deleted_at": {
                                    "time": "2023-01-01",
                                    "valid": true
                                },
                                "id": 101,
                                "latitude": 2000000,
                                "longitude": 3000000,
                                "province": "Province",
                                "updated_at": "2023-01-01"
                            }
                        ],
                        "award": 100,
                        "created_at": 2000000,
                        "deleted_at": {
                            "time": "2023-01-01",
                            "valid": true
                        },
                        "description;": "Description",
                        "has_alert": true,
                        "has_chat": false,
                        "id": 300,
                        "image": [
                            {
                                "created_at": 4000000,
                                "deleted_at": {
                                    "time": "2023-01-01",
                                    "valid": true
                                },
                                "id": 500,
                                "poster_id": 6000000,
                                "updated_at": "2023-01-01",
                                "url": "https://example.com/image.jpg"
                            }
                        ],
                        "special_type": "SpecialType",
                        "state": "State",
                        "status": "Status",
                        "tags": [
                            {
                                "created_at": 7000000,
                                "deleted_at": {
                                    "time": "2023-01-01",
                                    "valid": true
                                },
                                "id": 800,
                                "name": "TagName",
                                "posters": ["Poster1", "Poster2"],
                                "state": "State",
                                "updated_at": "2023-01-01"
                            }
                        ],
                        "telegram_id": "TelegramId",
                        "title": "Title",
                        "updated_at": "2023-01-01",
                        "user_id": 900,
                        "user_phone": "UserPhone"
                    }
                ],
                "username": "TestUser",
                "wallet": 1000
            }
        """.trimIndent()

        val gson = Gson()

        val getUserResponse = gson.fromJson(json, GetUserResponse::class.java)

        // Verify the deserialized values
        assertEquals(123, getUserResponse.id)
        assertEquals("TestUser", getUserResponse.username)
        assertEquals(1000, getUserResponse.wallet)
    }
}
