package com.alecbrando.musicplayer.data.service

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

interface FileApi {
    @GET("{url}")
    suspend fun downloadImage(@Path("url") url:String): Response<ResponseBody>

    companion object{
        var BASE_URL = "https://music-api-summer-2022.s3.amazonaws.com/"
        val instance  by lazy{
            Retrofit.Builder()
                .baseUrl("https://music-api-summer-2022.s3.amazonaws.com/")
                .build()
                .create(FileApi::class.java)
        }
    }
}