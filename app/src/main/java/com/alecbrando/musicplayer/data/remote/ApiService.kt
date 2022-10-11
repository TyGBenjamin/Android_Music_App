package com.alecbrando.musicplayer.data.remote

import com.alecbrando.musicplayer.domain.models.SongX
import com.alecbrando.musicplayer.util.Constants.BASE_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("music/songs")
    suspend fun getSongList(): Response<SongX>

    companion object{
        val apiInstance = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(ApiService::class.java)!!
    }

}