package com.alecbrando.musicplayer.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "songs")
data class SongX(
    @ColumnInfo
    val albumPicture: String,
    @ColumnInfo
    val artist: String,
    @ColumnInfo
    val genre: String,

    @PrimaryKey
    @ColumnInfo
    val id: Int,

    @ColumnInfo
    val mp3: String,

    @ColumnInfo
    val name: String
)