package com.example.haminjast.data.model

import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test

class MessageResponseTest {

    @Test
    fun messageResponseTest() {
        // Create test data
        val messageResponse = MessageResponse(
            id = 123,
            content = "Hello, world!",
            contentType = "text",
            date = "2023-01-01",
            status = "sent",
            senderID = 456,
            conversationID = 789,
            seqNumber = 1
        )

        // Verify the values
        assertEquals(123, messageResponse.id)
        assertEquals("Hello, world!", messageResponse.content)
        assertEquals("text", messageResponse.contentType)
        assertEquals("2023-01-01", messageResponse.date)
        assertEquals("sent", messageResponse.status)
        assertEquals(456, messageResponse.senderID)
        assertEquals(789, messageResponse.conversationID)
        assertEquals(1, messageResponse.seqNumber)
    }

    // If you want to test JSON deserialization, you can use Mockito to mock the deserialization process
    @Test
    fun jsonDeserializationTest() {
        val json = """
            {
                "message_id": 123,
                "content": "Hello, world!",
                "content_type": "text",
                "date": "2023-01-01",
                "status": "sent",
                "sender_id": 456,
                "conversation_id": 789,
                "seq_number": 1
            }
        """.trimIndent()

        val gson = Gson()

        val messageResponse = gson.fromJson(json, MessageResponse::class.java)

        // Verify the deserialized values
        assertEquals(123, messageResponse.id)
        assertEquals("Hello, world!", messageResponse.content)
        assertEquals("text", messageResponse.contentType)
        assertEquals("2023-01-01", messageResponse.date)
        assertEquals("sent", messageResponse.status)
        assertEquals(456, messageResponse.senderID)
        assertEquals(789, messageResponse.conversationID)
        assertEquals(1, messageResponse.seqNumber)
    }
}
