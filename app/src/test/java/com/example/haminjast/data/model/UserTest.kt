package com.example.haminjast.data.model

import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test

class UserTest {

    @Test
    fun userTest() {
        // Create test data
        val user = User(
            id = 1,
            markedPosters = emptyList(),
            posters = emptyList(),
            username = "testUser",
            wallet = 100
        )

        // Verify the values
        assertEquals(1, user.id)
        assertEquals(emptyList<MarkedPoster>(), user.markedPosters)
        assertEquals(emptyList<PosterU>(), user.posters)
        assertEquals("testUser", user.username)
        assertEquals(100, user.wallet)
    }

    // Similar tests can be written for other data classes like MarkedPoster, PosterU, AddressP, ImageP, TagP

    // For the MarkedPosterResponse class
    @Test
    fun markedPosterResponseTest() {
        // Create test data
        val markedPosterResponse = MarkedPosterResponse(message = "Success")

        // Verify the value
        assertEquals("Success", markedPosterResponse.message)
    }

    // If you want to test JSON serialization and deserialization, you can use Mockito to mock the process
    @Test
    fun jsonSerializationDeserializationTest() {
        val user = User(
            id = 1,
            markedPosters = emptyList(),
            posters = emptyList(),
            username = "testUser",
            wallet = 100
        )

        val gson = Gson()

        // Serialize to JSON
        val userJson = gson.toJson(user)

        // Deserialize back to object
        val newUser = gson.fromJson(userJson, User::class.java)

        // Verify the deserialized value
        assertEquals(user, newUser)
    }
}
