package com.example.haminjast.data.model

import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test

class SendMessageRequestTest {

    @Test
    fun sendMessageRequestTest() {
        // Create test data
        val content = "Hello, this is a test message"
        val conversationId = 123
        val id = 456L
        val posterId = 789
        val type = "text"

        val sendMessageRequest = SendMessageRequest(content, conversationId, id, posterId, type)

        // Verify the values
        assertEquals(content, sendMessageRequest.content)
        assertEquals(conversationId, sendMessageRequest.conversationId)
        assertEquals(id, sendMessageRequest.id)
        assertEquals(posterId, sendMessageRequest.posterId)
        assertEquals(type, sendMessageRequest.type)
    }

    // If you want to test JSON deserialization, you can use Mockito to mock the deserialization process
    @Test
    fun jsonDeserializationTest() {
        val json = """
            {
                "content": "Test content",
                "conversation_id": 123,
                "id": 456,
                "poster_id": 789,
                "type": "text"
            }
        """.trimIndent()

        val gson = Gson()

        val sendMessageRequest = gson.fromJson(json, SendMessageRequest::class.java)

        // Verify the deserialized values
        assertEquals("Test content", sendMessageRequest.content)
        assertEquals(123, sendMessageRequest.conversationId)
        assertEquals(456L, sendMessageRequest.id)
        assertEquals(789, sendMessageRequest.posterId)
        assertEquals("text", sendMessageRequest.type)
    }
}
