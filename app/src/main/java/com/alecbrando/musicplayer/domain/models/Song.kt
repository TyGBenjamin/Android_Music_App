package com.alecbrando.musicplayer.domain.models

data class Song(
    val lastUpdated: Long,
    val message: String,
    val nextPage: Int,
    val previousPage: Any,
    val songs: List<SongX>,
    val success: Boolean
)