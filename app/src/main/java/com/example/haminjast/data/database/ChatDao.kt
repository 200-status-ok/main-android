package com.example.haminjast.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.haminjast.data.model.ConversationCoverEntity
import com.example.haminjast.data.model.MessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllConversationCovers(conversationCoverEntities: List<ConversationCoverEntity>)

    @Query("SELECT * FROM conversation_covers WHERE id = :id")
    suspend fun getConversationCoverById(id: Long): ConversationCoverEntity?

    @Query("Update messages SET status = :status WHERE id = :id")
    suspend fun updateMessageStatus(id: Long, status: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(messageEntity: MessageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMessages(messageEntity: List<MessageEntity>)

    @Query("SELECT * FROM messages WHERE id = :id")
    suspend fun getMessageById(id: Long): MessageEntity?

    @Query("UPDATE conversation_covers SET lastMessageId = :newMessageID WHERE id = :conversationID")
    suspend fun updateConversationCoverLastMessageId(conversationID: Long, newMessageID: Long)

    @Query("UPDATE messages SET date = :date, status = :status WHERE id = :id")
    suspend fun updateMessageDateAndStatus(id: Long, date: Long, status: String)

    @Query(
        "SELECT * FROM conversation_covers LEFT JOIN messages ON conversation_covers.lastMessageId = messages.id"
    )
    fun getConversationCovers(): Flow<Map<ConversationCoverEntity, MessageEntity?>> // TODO if doesn't work, convert MessageEntity to List<MessageEntity>, check suspend

    @Query("SELECT * FROM conversation_covers WHERE id = :id")
    fun getConversationCover(id: Long): Flow<ConversationCoverEntity>

    @Query("SELECT * FROM messages WHERE conversationId = :conversationId ORDER BY date DESC")
    fun getConversationHistory(conversationId: Long): Flow<List<MessageEntity>>


    @Query("UPDATE conversation_covers SET lastReadMessageSeqNumber = :seqNumber WHERE id = :conversationId")
    fun updateLastReadMessageSeqNumber(conversationId: Long, seqNumber: Int)

    @Query("SELECT lastReadMessageSeqNumber FROM conversation_covers WHERE id = :conversationId")
    fun getLastReadMessageSeqNumber(conversationId: Long): Int
}