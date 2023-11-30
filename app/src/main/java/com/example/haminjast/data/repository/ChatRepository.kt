package com.example.haminjast.data.repository

import android.content.Context
import androidx.room.Room
import com.example.haminjast.data.database.ApplicationDataBase
import com.example.haminjast.data.database.ChatDao
import com.example.haminjast.data.model.ConversationCoverEntity
import com.example.haminjast.data.model.MessageEntity
import com.example.haminjast.data.network.ChatService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ChatRepository(
    private val chatDao: ChatDao, private val chatService: ChatService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    fun getConversationCovers(): Flow<Map<ConversationCoverEntity, MessageEntity>> {
        return chatDao.getConversationCovers()
    }

    suspend fun fetchConversationCovers(): Result<Unit> {
        return withContext(ioDispatcher) {
            val response = chatService.getConversations(
                authorization = "Bearer ",
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