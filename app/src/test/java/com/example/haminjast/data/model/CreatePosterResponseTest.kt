package com.example.haminjast.data.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import org.junit.Assert.assertEquals
import org.junit.Test

class CreatePosterResponseTest {

    @Test
    fun createPosterResponseTest() {
        // Create test data
        val addresses = listOf(
            CreatePosterResponse.Addresse(
                "Detail",
                1,
                "City",
                123,
                CreatePosterResponse.Addresse.DeletedAt("time", true),
                456,
                789,
                35,
                "Province",
                "updatedAt"
            )
        )

        val award = 42
        val createdAt = System.currentTimeMillis()
        val description = "Test Description"
        val id = 123
        val images = listOf(
            CreatePosterResponse.Image(
                createdAt,
                CreatePosterResponse.Image.DeletedAt("time", true),
                456,
                789,
                "updatedAt",
                "url"
            )
        )

        val specialType = "Special"
        val state = "State"
        val status = "Status"
        val tags = listOf(
            CreatePosterResponse.Tag(
                createdAt,
                CreatePosterResponse.Tag.DeletedAt("time", true),
                456,
                "TagName",
                emptyList(),
                "TagState",
                "tagUpdatedAt"
            )
        )

        val telegramId = "123456"
        val title = "Test Title"
        val updatedAt = "updatedAt"
        val userId = 789
        val userPhone = "7890123456"

        // Create a CreatePosterResponse instance for testing
        val createPosterResponse = CreatePosterResponse(
            addresses,
            award,
            createdAt,
            description,
            id,
            images,
            specialType,
            state,
            status,
            tags,
            telegramId,
            title,
            updatedAt,
            userId,
            userPhone
        )

        // Verify the values
        assertEquals(addresses, createPosterResponse.addresses)
        assertEquals(award, createPosterResponse.award)
        assertEquals(createdAt, createPosterResponse.createdAt)
        assertEquals(description, createPosterResponse.description)
        assertEquals(id, createPosterResponse.id)
        assertEquals(images, createPosterResponse.images)
        assertEquals(specialType, createPosterResponse.specialType)
        assertEquals(state, createPosterResponse.state)
        assertEquals(status, createPosterResponse.status)
        assertEquals(tags, createPosterResponse.tags)
        assertEquals(telegramId, createPosterResponse.telegramId)
        assertEquals(title, createPosterResponse.title)
        assertEquals(updatedAt, createPosterResponse.updatedAt)
        assertEquals(userId, createPosterResponse.userId)
        assertEquals(userPhone, createPosterResponse.userPhone)
    }

    // If you want to test JSON deserialization, you can use Mockito to mock the deserialization process
    @Test
    fun jsonDeserializationTest() {
        val json = """
            {
                "addresses": [
                    {
                        "address_detail": "Detail",
                        "address_id": 1,
                        "city": "City",
                        "created_at": 123,
                        "deleted_at": {"time": "time", "valid": true},
                        "id": 456,
                        "latitude": 789,
                        "longitude": 123,
                        "province": "Province",
                        "updated_at": "updatedAt"
                    }
                ],
                "award": 42,
                "created_at": 123456789,
                "description": "Test Description",
                "id": 123,
                "images": [
                    {
                        "created_at": 123,
                        "deleted_at": {"time": "time", "valid": true},
                        "id": 456,
                        "poster_id": 789,
                        "updated_at": "updatedAt",
                        "url": "url"
                    }
                ],
                "special_type": "Special",
                "state": "State",
                "status": "Status",
                "tags": [
                    {
                        "created_at": 123,
                        "deleted_at": {"time": "time", "valid": true},
                        "id": 456,
                        "name": "TagName",
                        "posters": [],
                        "state": "TagState",
                        "updated_at": "tagUpdatedAt"
                    }
                ],
                "telegram_id": "123456",
                "title": "Test Title",
                "updated_at": "updatedAt",
                "user_id": 789,
                "user_phone": "7890123456"
            }
        """.trimIndent()

        val gson = Gson() // Assuming Gson is available in your project

        val createPosterResponse = gson.fromJson(json, CreatePosterResponse::class.java)

        // Verify the deserialized values
        // You can use assertEquals for each field similar to the previous test
        assertEquals("Detail", createPosterResponse.addresses[0].addressDetail)
        assertEquals(42, createPosterResponse.award)
        assertEquals(123456789, createPosterResponse.createdAt)
        // Add more assertions based on your class structure
    }
}
