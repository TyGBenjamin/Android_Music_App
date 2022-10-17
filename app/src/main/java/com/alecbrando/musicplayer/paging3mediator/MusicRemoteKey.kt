package com.alecbrando.musicplayer.paging3mediator

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MusicRemoteKey (
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prev: Int?,
    val next: Int?
)