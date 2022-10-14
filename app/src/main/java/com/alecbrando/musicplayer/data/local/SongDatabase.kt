package com.alecbrando.musicplayer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alecbrando.musicplayer.data.model.Song

@Database(entities = [Song::class], version = 1 )
@TypeConverters(DatabaseConverter::class)
abstract class SongDatabase : RoomDatabase() {
    abstract fun songDao() : SongDao
}