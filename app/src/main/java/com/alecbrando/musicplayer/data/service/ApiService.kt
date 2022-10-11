package com.alecbrando.musicplayer.data.service

import com.alecbrando.musicplayer.domains.models.SongsWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/music/songs")
    suspend fun getAllSongs() : Response<SongsWrapper>

    @GET("music/genre")
    suspend fun getSongsByGenres(@Query("search") genre: String) : Response<SongsWrapper>
}