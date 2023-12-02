package com.example.haminjast.data.repository

import com.example.haminjast.User
import com.example.haminjast.data.database.ChatDao
import com.example.haminjast.data.model.ConversationCoverEntity
import com.example.haminjast.data.model.MessageEntity
import com.example.haminjast.data.model.SendMessageRequest
import com.example.haminjast.data.network.ChatService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ChatRepository constructor( //TODO pprivate constructor
    private val chatDao: ChatDao, private val chatService: ChatService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    fun getConversationCovers(): Flow<Map<ConversationCoverEntity, MessageEntity>> {
        return chatDao.getConversationCovers()
    }

    suspend fun fetchConversationCovers(): Result<Unit> {
        return withContext(ioDispatcher) {
            val response = chatService.getConversations(
                authorization = "Bearer ",//TODO
            )

            if (response.isSuccessful) {
                response.body()?.let { conversationCoverResponse ->
                    val conversationCoverEntities = conversationCoverResponse.map {
//                        chatDao.insertMessage(MessageEntity.fromResponse(it.lastMessage))//TODO
                        ConversationCoverEntity.fromResponse(it)
                    }
                    chatDao.insertAllConversationCovers(conversationCoverEntities)
                }
                return@withContext Result.success(Unit)

            } else {
                return@withContext Result.failure(Throwable(response.message()))
            }
        }
    }

    fun getConversationHistory(conversationID: Long): Flow<List<MessageEntity>> {
        return chatDao.getConversationHistory(conversationID)
    }

    suspend fun fetchConversationHistory(conversationID: Long): Result<Unit> {
        return withContext(ioDispatcher) {
            val response = chatService.getConversationHistory(
                authorization = "Bearer ", //TODO
                conversationID = conversationID.toString(),
                pageID = 1,
                pageSize = 100
            )
            return@withContext if (response.isSuccessful) {
                response.body()?.let { conversationHistoryResponse ->
                    val messageEntities = conversationHistoryResponse[0].messages.map {
                        MessageEntity.fromResponse2(it)
                    }
                    chatDao.insertAllMessages(messageEntities)
                }
                Result.success(Unit)

            } else {
                Result.failure(Throwable(response.message()))
            }
        }
    }

    suspend fun sendMessage( // TODO make usecase and change logic
        conversationID: Long,
        content: String,
        contentType: String
    ): Result<Unit> {
        return withContext(ioDispatcher) {
//            chatDao.insertMessage(MessageEntity()) //TODO
//
//            chatDao.updateConversationCoverLastMessageId(conversationID,)

            val response = chatService.sendMessage(
                sendMessageRequest = SendMessageRequest(
                    content = content,
                    conversationId = conversationID.toInt(),
                    posterId = 0,
                    receiverId = 0,
                    senderId = User.id.toInt(),
                    type = contentType
                )
            )
            return@withContext if (response.isSuccessful) {
//                chatDao.updateMessageDateAndStatus(,,"sent")

                fetchConversationHistory(conversationID)
                Result.success(Unit)



            } else {
//                chatDao.updateMessageDateAndStatus(,,"failed")
                Result.failure(Throwable(response.message()))
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ChatRepository? = null
        fun getInstance(chatDao: ChatDao, chatService: ChatService): ChatRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: ChatRepository(chatDao, chatService).also { INSTANCE = it }
            }
    }
}