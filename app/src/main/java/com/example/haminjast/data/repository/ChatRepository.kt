package com.example.haminjast.data.repository

import android.util.Log
import com.example.haminjast.User
import com.example.haminjast.User.token
import com.example.haminjast.data.database.ChatDao
import com.example.haminjast.data.model.ConversationCoverEntity
import com.example.haminjast.data.model.MessageEntity
import com.example.haminjast.data.model.MessageReceivedUpdate
import com.example.haminjast.data.model.SendMessageRequest
import com.example.haminjast.data.network.ChatService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ChatRepository constructor( //TODO private constructor
    private val chatDao: ChatDao, private val chatService: ChatService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    fun getConversationCovers(): Flow<Map<ConversationCoverEntity, MessageEntity?>> {
        return chatDao.getConversationCovers()
    }

    suspend fun fetchConversationCovers(): Result<Unit> {
        return withContext(ioDispatcher) {
            try {
                Log.d("modar", "fetch conversation covers");
                val response = chatService.getConversations(
                    authorization = "Bearer $token",//TODO
                )

                if (response.isSuccessful) {
                    Log.d("modar", "fetch conversation covers success");
                    response.body()?.let { conversationCoverResponse ->
                        Log.d(
                            "modar",
                            "fetch conversation covers response $conversationCoverResponse"
                        );

                        val conversationCoverEntities = mutableListOf<ConversationCoverEntity>()
                        val conversationCoverLastMessageEntities = mutableListOf<MessageEntity>()
                        conversationCoverResponse.forEach {
                            conversationCoverEntities.add(ConversationCoverEntity.fromResponse(it))
                            conversationCoverLastMessageEntities.add(
                                MessageEntity.fromConversationCoverResponseLastMessage(
                                    it.lastMessage
                                )
                            )
                        }

                        chatDao.insertAllMessages(conversationCoverLastMessageEntities)
                        chatDao.insertAllConversationCovers(conversationCoverEntities)
                    }
                    return@withContext Result.success(Unit)

                } else {
                    Log.d("modar", "fetch conversation covers failed ${response.message()}")
                    Log.d("modar", "fetch conversation covers failed ${response.code()}")
                    return@withContext Result.failure(Throwable(response.message()))
                }
            } catch (e: Exception) {
                Log.e("modar", "ws error ${e.message}")
                return@withContext Result.failure(Throwable(e.message))
            }

        }
    }

    fun getConversationHistory(conversationID: Long): Flow<List<MessageEntity>> {
        return chatDao.getConversationHistory(conversationID)
    }

    suspend fun fetchConversationHistory(conversationID: Long): Result<Unit> {
        return withContext(ioDispatcher) {
            val response = chatService.getConversationHistory(
                authorization = "Bearer $token", //TODO
                conversationID = conversationID.toString(),
                pageID = 1,
                pageSize = 100
            )
            return@withContext if (response.isSuccessful) {
                response.body()?.let { conversationHistoryResponse ->
                    val messageEntities = conversationHistoryResponse.messages.map {
                        MessageEntity.fromConversationHistoryResponseMessage(it)
                    }
                    chatDao.insertAllMessages(messageEntities)
                }
                Result.success(Unit)

            } else {
                Result.failure(Throwable(response.message()))
            }
        }
    }

    suspend fun sendMessage( // TODO make useCase and change logic
        conversationID: Long,
        posterID: Long,
        content: String,
        contentType: String
    ): Result<Unit> {
        return withContext(ioDispatcher) {

            val messageID = System.currentTimeMillis()

            chatDao.insertMessage(
                MessageEntity(
                    id = messageID,
                    content = content,
                    contentType = contentType,
                    date = System.currentTimeMillis(),
                    status = "pending",
                    senderID = User.id,
                    conversationID = conversationID,
                    seqNumber = 0 //TODO Fakhar
                )
            )

            chatDao.updateConversationCoverLastMessageId(conversationID, messageID)

            val sendMessageRequest = SendMessageRequest(
                id = messageID,
                conversationId = conversationID.toInt(),
                posterId = posterID.toInt(),
                content = content,
                type = contentType
            )

            val response = chatService.sendMessage(
                authorization = "Bearer $token",
                acceptHeader = "application/json",
                contentTypeHeader = "application/json",
                sendMessageRequest = sendMessageRequest
            )
            val messageResponse = response.body()

            return@withContext if (response.isSuccessful) {
                messageResponse?.let {
                    chatDao.updateMessageDateAndStatus(
                        messageID,
                        System.currentTimeMillis(),//TODO UNIX
                        "unread"
                    )
                }
//                fetchConversationHistory(conversationID) TODO remove: no need probably
                Result.success(Unit)

            } else {
                messageResponse?.let {
                    chatDao.updateMessageDateAndStatus(
                        messageID,
                        System.currentTimeMillis(),//TODO UNIX
                        "failed"
                    )
                }
                Result.failure(Throwable(response.message()))

            }
        }
    }

    suspend fun onMessageReceived(messageReceivedUpdate: MessageReceivedUpdate) {
        val messageEntity = MessageEntity.fromMessageReceivedUpdate(messageReceivedUpdate)
        withContext(ioDispatcher) {// todo transaction
            chatDao.insertMessage(messageEntity)
            chatDao.updateConversationCoverLastMessageId(
                messageEntity.conversationID,
                messageEntity.id
            )
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