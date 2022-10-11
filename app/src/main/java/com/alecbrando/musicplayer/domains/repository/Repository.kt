package com.alecbrando.musicplayer.domains.repository

import com.alecbrando.musicplayer.domains.models.SongsWrapper
import com.alecbrando.musicplayer.resources.Resource

interface Repository {
    suspend fun getAllSongs(): Resource<SongsWrapper>
    suspend fun getSongsByGenres(genre: String): Resource<SongsWrapper>
}