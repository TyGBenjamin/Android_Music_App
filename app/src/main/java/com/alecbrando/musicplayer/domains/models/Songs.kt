package com.alecbrando.musicplayer.domains.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Songs(
    val id : String,
    val name: String,
    val artist: String,
    val genre: String,
    val albumPicture: String,
    val mp3: String
)
//
//@Entity(tableName = "notes")
//data class Note(
//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo
//    val id : Int = 0,
//    @ColumnInfo
//    val title: String,
//    @ColumnInfo
//    val body:String,
//
//    )