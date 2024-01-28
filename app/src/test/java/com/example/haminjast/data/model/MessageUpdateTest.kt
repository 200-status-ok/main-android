package com.example.haminjast.data.model

import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test

class MessageUpdateTest {

    @Test
    fun messageUpdateTest() {
        // Create test data
        val messageUpdate = MessageUpdate(
            content = "Hello, world!",
            conversationId = 123,
            id = 456,
            receiverId = 789,
            sequenceNo = 1,
            senderId = 101,
            status = "delivered",
            time = System.currentTimeMillis(),
            type = "text"
        )

        // Verify the values
        assertEquals("Hello, world!", messageUpdate.content)
        assertEquals(123, messageUpdate.conversationId)
        assertEquals(456, messageUpdate.id)
        assertEquals(789, messageUpdate.receiverId)
        assertEquals(1, messageUpdate.sequenceNo)
        assertEquals(101, messageUpdate.senderId)
        assertEquals("delivered", messageUpdate.status)
        assertEquals("text", messageUpdate.type)
    }

    // If you want to test JSON deserialization, you can use Mockito to mock the deserialization process
    @Test
    fun jsonDeserializationTest() {
        val json = """
            {
                "content": "Hello, world!",
                "conversation_id": 123,
                "id": 456,
                "receiver_id": 789,
                "sequence_no": 1,
                "sender_id": 101,
                "status": "delivered",
                "time": ${System.currentTimeMillis()},
                "type": "text"
            }
        """.trimIndent()

        val gson = Gson()

        val messageUpdate = gson.fromJson(json, MessageUpdate::class.java)

        // Verify the deserialized values
        assertEquals("Hello, world!", messageUpdate.content)
        assertEquals(123, messageUpdate.conversationId)
        assertEquals(456, messageUpdate.id)
        assertEquals(789, messageUpdate.receiverId)
        assertEquals(1, messageUpdate.sequenceNo)
        assertEquals(101, messageUpdate.senderId)
        assertEquals("delivered", messageUpdate.status)
        assertEquals("text", messageUpdate.type)
    }
}
