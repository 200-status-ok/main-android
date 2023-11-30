package com.example.haminjast.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.haminjast.data.model.ConversationCoverEntity
import com.example.haminjast.data.model.MessageEntity
import com.example.haminjast.data.model.Poster
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

@Dao
interface ChatDao {
//    @Query("SELECT * FROM conversation_covers")
//    fun getConversationCovers(): Flow<List<ConversationCoverEntity>> //TODO check suspend

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllConversationCovers(conversationCoverEntities: List<ConversationCoverEntity>)

    @Query("SELECT * FROM conversation_covers WHERE id = :id")
    suspend fun getConversationCoverById(id: Long): ConversationCoverEntity?

    @Query("Update messages SET status = :status WHERE id = :id")
    suspend fun updateMessageStatus(id: Long, status: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(messageEntity: MessageEntity)

    @Query("SELECT * FROM messages WHERE id = :id")
    suspend fun getMessageById(id: Long): MessageEntity?

    @Query("UPDATE conversation_covers SET lastMessageId = :newId WHERE id = :id")
    suspend fun updateConversationCoverLastMessageId(id: Long, newId: Long)

    @Query("UPDATE messages SET date = :date, status = :status WHERE id = :id")
    suspend fun updateMessageDateAndStatus(id: Long, date: Long, status: String)

    @Query(
        "SELECT * FROM conversation_covers JOIN messages ON conversation_covers.lastMessageId = messages.id"
    )
    fun getConversationCovers(): Flow<Map<ConversationCoverEntity, MessageEntity>> // TODO if doesn't work, convert MessageEntity to List<MessageEntity>, check suspend
}