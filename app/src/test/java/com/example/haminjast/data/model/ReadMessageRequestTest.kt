package com.example.haminjast.data.model

import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test

class ReadMessageRequestTest {

    @Test
    fun readMessageRequestTest() {
        // Create test data
        val messageIds = listOf(1L, 2L, 3L)
        val senderId = 101
        val readMessageRequest = ReadMessageRequest(messageIds, senderId)

        // Verify the values
        assertEquals(messageIds, readMessageRequest.messageIds)
        assertEquals(senderId, readMessageRequest.senderId)
    }

    // If you want to test JSON deserialization, you can use Mockito to mock the deserialization process
    @Test
    fun jsonDeserializationTest() {
        val json = """
            {
                "message_ids": [1, 2, 3],
                "sender_id": 101
            }
        """.trimIndent()

        val gson = Gson()

        val readMessageRequest = gson.fromJson(json, ReadMessageRequest::class.java)

        // Verify the deserialized values
        assertEquals(listOf(1L, 2L, 3L), readMessageRequest.messageIds)
        assertEquals(101, readMessageRequest.senderId)
    }
}
