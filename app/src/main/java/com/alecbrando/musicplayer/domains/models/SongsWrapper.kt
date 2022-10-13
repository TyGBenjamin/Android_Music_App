package com.alecbrando.musicplayer.domains.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.alecbrando.musicplayer.data.typeconverters.TypeConvertersHelper

@TypeConverters(TypeConvertersHelper::class)
data class SongsWrapper(
    val songs : List<Songs>
)