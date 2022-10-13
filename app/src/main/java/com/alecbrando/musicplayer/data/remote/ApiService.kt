package com.alecbrando.musicplayer.data.remote

import com.alecbrando.musicplayer.domain.models.SongList
import com.alecbrando.musicplayer.domain.models.SongX
import com.alecbrando.musicplayer.util.Constants.BASE_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("music/songs")
    suspend fun getSongList(): Response<SongList>

    @GET("music/genre")
    suspend fun getGenres(@Query("search") genre: String) : Response<SongList>



}