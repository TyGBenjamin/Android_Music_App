package com.alecbrando.musicplayer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.alecbrando.musicplayer.data.typeconverters.TypeConvertersHelper
import com.alecbrando.musicplayer.domains.models.Songs

@Database(entities = [Songs::class], version = 2)
@TypeConverters(TypeConvertersHelper::class)
abstract class SongsDatabase : RoomDatabase(){
    abstract fun songsDao(): SongsDao
}