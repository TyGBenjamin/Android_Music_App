package com.alecbrando.musicplayer.domains.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class Songs(
    @PrimaryKey(autoGenerate = false)
    val id : String,
    val name: String,
    val artist: String,
    val genre: String,
    val albumPicture: String,
    val mp3: String
)
