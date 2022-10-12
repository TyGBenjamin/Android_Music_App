package com.alecbrando.musicplayer.data.repository

import android.util.Log
import com.alecbrando.musicplayer.data.remote.ApiService
import com.alecbrando.musicplayer.domain.model.SongList
import com.alecbrando.musicplayer.domain.repository.Repository
import com.alecbrando.musicplayer.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object RepositoryImpl: Repository {

    private val apiInstance by lazy { ApiService.apiInstance}
    override suspend fun getSongs(): Resource<SongList> = withContext(Dispatchers.IO) {
        return@withContext try {
            val res = apiInstance.getSongs()
            Log.d("Repo Imp", "getSongs: ${res.body()}")
            if(res.isSuccessful && res.body() !=null){
                Resource.Success(res.body()!!)
            }
            else{
                Resource.Error("An Error Occurred")
            }

        }        catch(e : Exception){
            Resource.Error(e.message.toString())
        }
    }
}