package com.alecbrando.musicplayer.data.remote

import com.alecbrando.musicplayer.data.model.SongList
import com.alecbrando.musicplayer.util.Constants.BASE_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("music/songs")
    suspend fun getSongs(): Response<SongList>

    @GET("music/genre")
    suspend fun getGenre(@Query("search") genre: String): Response<SongList>

    companion object {
        val apiInstance =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService::class.java)
    }
}