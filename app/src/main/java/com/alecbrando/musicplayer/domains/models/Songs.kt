package com.alecbrando.musicplayer.domains.models

data class Songs(
    val id : String,
    val name: String,
    val artist: String,
    val genre: String,
    val albumPicture: String,
    val mp3: String
)