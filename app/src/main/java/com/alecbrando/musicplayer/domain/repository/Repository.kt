package com.alecbrando.musicplayer.domain.repository

import com.alecbrando.musicplayer.domain.models.SongListWrapper
import com.alecbrando.musicplayer.util.Resource
import retrofit2.Response


interface Repository {
    suspend fun getSongs(): Resource<SongListWrapper>
    suspend fun getSongsByPage(page: Int): Resource<SongListWrapper>
    suspend fun getSongsByGenre(genre: String): Resource<SongListWrapper>
}