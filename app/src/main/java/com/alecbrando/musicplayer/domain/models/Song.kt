package com.alecbrando.musicplayer.domain.models

data class Song(
    val id: Int,
    val name: String,
    val artist: String,
    val genre: String,
    val albumPicture: String,
    val mp3: String
)
