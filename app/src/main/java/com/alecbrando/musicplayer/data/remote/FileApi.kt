package com.alecbrando.musicplayer.data.remote

import com.alecbrando.musicplayer.util.Constants
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET

interface FileApi {
    @GET("/wp-content/uploads/2022/02/220849-scaled.jpg")

    suspend fun downloadSong(): Response<ResponseBody>

    companion object{
        val instance by lazy{
            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .build()
                .create(FileApi::class.java)
        }
    }
}