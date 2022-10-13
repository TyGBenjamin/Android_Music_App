package com.alecbrando.musicplayer.domain.repository

import com.alecbrando.musicplayer.domain.models.SongList
import com.alecbrando.musicplayer.domain.models.SongX
import com.alecbrando.musicplayer.util.Resource

interface Repository {
    suspend fun getSongList() : Resource<SongList>
    suspend fun getGenres(genre: String): Resource<SongList>

}