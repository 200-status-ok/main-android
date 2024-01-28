package com.example.haminjast.data.model

import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test

class UploadImageResponseTest {

    @Test
    fun uploadImageResponseTest() {
        // Create test data
        val imageUrl = "https://example.com/image.jpg"
        val uploadImageResponse = UploadImageResponse(imageUrl)

        // Verify the value
        assertEquals(imageUrl, uploadImageResponse.url)
    }

    // If you want to test JSON deserialization, you can use Mockito to mock the deserialization process
    @Test
    fun jsonDeserializationTest() {
        val json = """
            {
                "url": "https://example.com/image.jpg"
            }
        """.trimIndent()

        val gson = Gson()

        val uploadImageResponse = gson.fromJson(json, UploadImageResponse::class.java)

        // Verify the deserialized value
        assertEquals("https://example.com/image.jpg", uploadImageResponse.url)
    }
}
