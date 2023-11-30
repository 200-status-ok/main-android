package com.example.haminjast.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.haminjast.data.model.Poster

@Dao
interface PosterDao {
    @Insert(entity = Poster::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<Poster>)

    @Upsert(entity = Poster::class)
    suspend fun upsert(repos: List<Poster>)

    @Query("SELECT * FROM posters")
    fun getAllData() : PagingSource<Int,Poster>

    @Query("DELETE FROM posters")
    suspend fun deleteData()

}