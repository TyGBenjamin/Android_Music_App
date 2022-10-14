package com.alecbrando.musicplayer.domain.repository

import com.alecbrando.musicplayer.data.model.SongList
import com.alecbrando.musicplayer.util.Resource

interface Repository {
    suspend fun getSongs() : Resource<SongList>
    suspend fun getGenre(genre:String) : Resource<SongList>
//    suspend fun getAnimeById(id: String): Resource<AnimeWrapper>
}