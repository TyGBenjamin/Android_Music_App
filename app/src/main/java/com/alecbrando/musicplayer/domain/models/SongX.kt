package com.alecbrando.musicplayer.domain.models

data class SongX(
    val albumPicture: String,
    val artist: String,
    val genre: String,
    val id: Int,
    val mp3: String,
    val name: String
)