package com.example.haminjast.data.model

import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test

class SendMessageResponseTest {

    @Test
    fun sendMessageResponseTest() {
        // Create test data
        val message = "Message sent successfully"
        val sendMessage = SendMessageResponse.SendMessage(
            "Hello, this is a test message",
            123,
            456L,
            789,
            101,
            1,
            "Delivered",
            System.currentTimeMillis(),
            "text"
        )

        val sendMessageResponse = SendMessageResponse(message, sendMessage)

        // Verify the values
        assertEquals(message, sendMessageResponse.message)
        assertEquals(sendMessage.content, sendMessageResponse.sendMessage.content)
        assertEquals(sendMessage.conversationId, sendMessageResponse.sendMessage.conversationId)
        assertEquals(sendMessage.id, sendMessageResponse.sendMessage.id)
        assertEquals(sendMessage.receiverId, sendMessageResponse.sendMessage.receiverId)
        assertEquals(sendMessage.senderId, sendMessageResponse.sendMessage.senderId)
        assertEquals(sendMessage.sequenceNo, sendMessageResponse.sendMessage.sequenceNo)
        assertEquals(sendMessage.status, sendMessageResponse.sendMessage.status)
        assertEquals(sendMessage.time, sendMessageResponse.sendMessage.time)
        assertEquals(sendMessage.type, sendMessageResponse.sendMessage.type)
    }


    // If you want to test JSON deserialization, you can use Mockito to mock the deserialization process
    @Test
    fun jsonDeserializationTest() {
        val json = """
            {
                "message": "Message sent successfully",
                "send_message": {
                    "content": "Hello, this is a test message",
                    "conversation_id": 123,
                    "id": 456,
                    "receiver_id": 789,
                    "sender_id": 101,
                    "sequence_no": 1,
                    "status": "Delivered",
                    "time": ${System.currentTimeMillis()},
                    "type": "text"
                }
            }
        """.trimIndent()

        val gson = Gson()

        val sendMessageResponse = gson.fromJson(json, SendMessageResponse::class.java)

        // Verify the deserialized values
        assertEquals("Message sent successfully", sendMessageResponse.message)
        assertEquals("Hello, this is a test message", sendMessageResponse.sendMessage.content)
        assertEquals(123, sendMessageResponse.sendMessage.conversationId)
        assertEquals(456L, sendMessageResponse.sendMessage.id)
        assertEquals(789, sendMessageResponse.sendMessage.receiverId)
        assertEquals(101, sendMessageResponse.sendMessage.senderId)
        assertEquals(1, sendMessageResponse.sendMessage.sequenceNo)
        assertEquals("Delivered", sendMessageResponse.sendMessage.status)
        assertEquals("text", sendMessageResponse.sendMessage.type)
    }
}
