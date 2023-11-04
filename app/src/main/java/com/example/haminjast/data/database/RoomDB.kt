package com.example.haminjast.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.haminjast.data.model.AddressListConverter
import com.example.haminjast.data.model.LocationConverter
import com.example.haminjast.data.model.Poster
import com.example.haminjast.data.model.StringListConverter
import com.example.haminjast.data.model.TagConverter
import com.example.haminjast.data.model.TagListConverter

@Database(
    entities = [Poster::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(AddressListConverter::class,
    LocationConverter::class,
    TagConverter::class,
    StringListConverter::class,
    TagListConverter::class
)
abstract class RoomDB : RoomDatabase() {
    abstract fun posterDao(): PosterDAO

    companion object {
        @Volatile
        private var INSTANCE: RoomDB? = null
        fun getInstance(context: Context): RoomDB =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        fun refreshInstance(context: Context): RoomDB {
            INSTANCE = null
            return buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                RoomDB::class.java, "posters.db"
            )
                .fallbackToDestructiveMigration()
                .build()
    }


}