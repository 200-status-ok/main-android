package com.example.haminjast.data.model

import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test

class VORTest {

    @Test
    fun vorTest() {
        // Create test data
        val vor = VOR(
            id = 1,
            message = "Test message",
            token = "TestToken123"
        )

        // Verify the values
        assertEquals(1, vor.id)
        assertEquals("Test message", vor.message)
        assertEquals("TestToken123", vor.token)
    }

    // If you want to test JSON serialization and deserialization, you can use Mockito to mock the process
    @Test
    fun jsonSerializationDeserializationTest() {
        val vor = VOR(
            id = 1,
            message = "Test message",
            token = "TestToken123"
        )

        val gson = Gson()

        // Serialize to JSON
        val vorJson = gson.toJson(vor)

        // Deserialize back to object
        val newVOR = gson.fromJson(vorJson, VOR::class.java)

        // Verify the deserialized value
        assertEquals(vor, newVOR)
    }
}
