package com.alecbrando.musicplayer.domain.model

data class Data(
    val success: Boolean,
    val message: String?,
    val previousPage: Int?,
    val nextPage: Int?,
    val songs: List<Song>
)
