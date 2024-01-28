package com.example.haminjast.data.model

import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test

class ReadMessageResponseTest {

    @Test
    fun readMessageResponseTest() {
        // Create test data
        val message = "Message read successfully"
        val readMessageResponse = ReadMessageResponse(message)

        // Verify the values
        assertEquals(message, readMessageResponse.message)
    }

    // If you want to test JSON deserialization, you can use Mockito to mock the deserialization process
    @Test
    fun jsonDeserializationTest() {
        val json = """
            {
                "message": "Message read successfully"
            }
        """.trimIndent()

        val gson = Gson()

        val readMessageResponse = gson.fromJson(json, ReadMessageResponse::class.java)

        // Verify the deserialized values
        assertEquals("Message read successfully", readMessageResponse.message)
    }
}
