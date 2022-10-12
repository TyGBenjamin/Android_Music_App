package com.alecbrando.musicplayer.data.remote

import com.alecbrando.musicplayer.domain.model.SongList
import com.alecbrando.musicplayer.util.BASE_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("")
    suspend fun getSongs(): Response<SongList>

//    @GET("anime/{id}")
//    suspend fun getAnimeById(@Path("id") id: String): Response<AnimeWrapper>

    companion object {
        val apiInstance =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService::class.java)
    }
}