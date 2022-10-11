package com.alecbrando.musicplayer.domain.model

data class Song(
    val albumPicture: String,
    val artist: String,
    val genre: String,
    val id: Int,
    val mp3: String,
    val name: String
)