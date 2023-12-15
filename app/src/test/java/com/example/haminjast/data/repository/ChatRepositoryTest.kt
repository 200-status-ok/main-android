package com.example.haminjast.data.repository

import com.example.haminjast.data.database.ChatDao
import com.example.haminjast.data.model.ConversationCoverEntity
import com.example.haminjast.data.model.ConversationHistoryResponse
import com.example.haminjast.data.model.MessageEntity
import com.example.haminjast.data.model.SendMessageResponse
import com.example.haminjast.data.network.ChatService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class ChatRepositoryTest {

    @Mock
    private lateinit var chatDao: ChatDao

    @Mock
    private lateinit var chatService: ChatService

    @Test
    fun getConversationCovers_success() {
        val conversationCoverEntities = mutableMapOf(
            ConversationCoverEntity(
                id = 1,
                title = "Conversation 1",
                imageUrl = "https://www.google.com",
                lastMessageId = 1,
                posterID = 1,
                isOwner = true,
                lastReadMessageSeqNumber = 1
            ) to MessageEntity(
                id = 1,
                content = "Message 1",
                contentType = "text",
                date = 1,
                status = "sent",
                senderID = 1,
                conversationID = 1,
                seqNumber = 1
            ),
            ConversationCoverEntity(
                id = 2,
                title = "Conversation 2",
                imageUrl = "https://www.google.com",
                lastMessageId = 2,
                posterID = 2,
                isOwner = true,
                lastReadMessageSeqNumber = 2
            ) to MessageEntity(
                id = 2,
                content = "Message 2",
                contentType = "text",
                date = 2,
                status = "sent",
                senderID = 2,
                conversationID = 2,
                seqNumber = 2
            )
        )

        whenever(chatDao.getConversationCovers()).thenReturn(
            MutableStateFlow(
                conversationCoverEntities
            )
        )

        val chatRepository = ChatRepository(chatDao, chatService)
        runBlocking {
            val result = chatRepository.getConversationCovers().first()
            Assert.assertEquals(conversationCoverEntities, result)
        }
    }

    @Test
    fun getConversationHistory_success() {
        val messages = listOf(
            MessageEntity(
                id = 1,
                content = "Message 1",
                contentType = "text",
                date = 1,
                status = "sent",
                senderID = 1,
                conversationID = 1,
                seqNumber = 1
            ),
            MessageEntity(
                id = 2,
                content = "Message 2",
                contentType = "text",
                date = 2,
                status = "sent",
                senderID = 2,
                conversationID = 2,
                seqNumber = 2
            )
        )

        whenever(chatDao.getConversationHistory(any())).thenReturn(MutableStateFlow(messages))

        val chatRepository = ChatRepository(chatDao, chatService)

        runBlocking {
            val result = chatRepository.getConversationHistory(1).first()
            Assert.assertEquals(messages, result)
        }
    }

    @Test
    fun fetchConversationHistory_success() = runBlocking {
        val conversationHistoryResponse = ConversationHistoryResponse(
            messages = listOf(
                ConversationHistoryResponse.Message(
                    content = "Message 1",
                    conversationId = 1,
                    createdAt = "2021-06-01T00:00:00.000Z",
                    deletedAt = "2021-06-01T00:00:00.000Z",
                    id = 1,
                    isSend = true,
                    receiverId = 1,
                    senderId = 1,
                    status = "sent",
                    type = "text",
                    updatedAt = "2021-06-01T00:00:00.000Z"
                ),
                ConversationHistoryResponse.Message(
                    content = "Message 2",
                    conversationId = 2,
                    createdAt = "2021-06-01T00:00:00.000Z",
                    deletedAt = "2021-06-01T00:00:00.000Z",
                    id = 2,
                    isSend = true,
                    receiverId = 2,
                    senderId = 2,
                    status = "sent",
                    type = "text",
                    updatedAt = "2021-06-01T00:00:00.000Z"
                )
            ),
            userId = 1
        )

        whenever(chatService.getConversationHistory(any(), any(), any(), any())).thenReturn(
            Response.success(conversationHistoryResponse)
        )

        val chatRepository = ChatRepository(chatDao, chatService)
        val result = chatRepository.fetchConversationHistory(1)

        Assert.assertTrue(result.isSuccess)
        Assert.assertEquals(conversationHistoryResponse.messages.size, 2)
    }

    @Test
    fun sendMessage_success() = runBlocking {
        val messageId = 1L

        whenever(
            chatService.sendMessage(
                any(),
                any(),
                any(),
                any()
            )
        ).thenReturn(
            Response.success(
                SendMessageResponse(
                    message = "Message",
                    sendMessage = SendMessageResponse.SendMessage(
                        content = "Message",
                        conversationId = 1,
                        createdAt = "2021-06-01T00:00:00.000Z",
                        deletedAt = "2021-06-01T00:00:00.000Z",
                        id = messageId,
                        isSend = true,
                        receiverId = 1,
                        senderId = 1,
                        status = "sent",
                        type = "text",
                        updatedAt = "2021-06-01T00:00:00.000Z"
                    )
                )
            )
        )

        val chatRepository = ChatRepository(chatDao, chatService)
        val result = chatRepository.sendMessage(2, 1, "Message", "text")

        Assert.assertTrue(result.isSuccess)
    }
}
