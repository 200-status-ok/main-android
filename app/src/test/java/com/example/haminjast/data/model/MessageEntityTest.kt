package com.example.haminjast.data.model

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MessageEntityTest {

    @Test
    fun `should create valid message entity from conversation history response message`() {
        val messageResponse = ConversationHistoryResponse.Message(
            id = 1,
            content = "This is a message",
            type = "text",
            createdAt = 1234567890,
            status = "sent",
            senderId = 1,
            conversationId = 1,
            sequenceNo = 1234567890,
            receiverId = 2,
            updatedAt = 1234567890
        )

        val messageEntity = MessageEntity.fromConversationHistoryResponseMessage(messageResponse)

        Assert.assertEquals(messageResponse.id, messageEntity.id)
        Assert.assertEquals(messageResponse.content, messageEntity.content)
        Assert.assertEquals(messageResponse.type, messageEntity.contentType)
        Assert.assertEquals(messageResponse.createdAt, messageEntity.date)
        Assert.assertEquals(messageResponse.status, messageEntity.status)
        Assert.assertEquals(messageResponse.senderId.toLong(), messageEntity.senderID)
        Assert.assertEquals(messageResponse.conversationId.toLong(), messageEntity.conversationID)
        Assert.assertEquals(messageResponse.sequenceNo.toLong(), messageEntity.seqNumber)
    }

    @Test
    fun `should create valid message entity from conversation cover response last message`() {
        val lastMessage = ConversationCoverResponse.ConversationCoverResponseItem.LastMessage(
            id = 2,
            content = "This is another message",
            type = "text",
            createdAt = 987654321,
            status = "read",
            senderId = 2,
            conversationId = 2,
            sequenceNo = 1234567890,
            updatedAt = 1234567890,
            receiverId = 1
        )

        val messageEntity = MessageEntity.fromConversationCoverResponseLastMessage(lastMessage)

        Assert.assertEquals(lastMessage.id, messageEntity.id)
        Assert.assertEquals(lastMessage.content, messageEntity.content)
        Assert.assertEquals(lastMessage.type, messageEntity.contentType)
        Assert.assertEquals(lastMessage.createdAt, messageEntity.date)
        Assert.assertEquals(lastMessage.status, messageEntity.status)
        Assert.assertEquals(lastMessage.senderId.toLong(), messageEntity.senderID)
        Assert.assertEquals(lastMessage.conversationId.toLong(), messageEntity.conversationID)
        //TODO: verify seqNumber
    }

    @Test
    fun `should create valid message entity from message received update`() {
        val messageReceivedUpdate = MessageUpdate(
            id = 3,
            content = "This is the latest message",
            type = "text",
            time = 1590738923,
            status = "delivered",
            senderId = 3,
            conversationId = 3,
            sequenceNo = 456789012,
            receiverId = 1
        )

        val messageEntity = MessageEntity.fromMessageReceivedUpdate(messageReceivedUpdate)

        Assert.assertEquals(messageReceivedUpdate.id, messageEntity.id)
        Assert.assertEquals(messageReceivedUpdate.content, messageEntity.content)
        Assert.assertEquals(messageReceivedUpdate.type, messageEntity.contentType)
        Assert.assertEquals(messageReceivedUpdate.time, messageEntity.date)
        Assert.assertEquals(messageReceivedUpdate.status, messageEntity.status)
        Assert.assertEquals(messageReceivedUpdate.senderId.toLong(), messageEntity.senderID)
        Assert.assertEquals(messageReceivedUpdate.conversationId.toLong(), messageEntity.conversationID)
        Assert.assertEquals(messageReceivedUpdate.sequenceNo.toLong(), messageEntity.seqNumber)
    }
}
