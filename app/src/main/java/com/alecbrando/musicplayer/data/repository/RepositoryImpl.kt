package com.alecbrando.musicplayer.data.repository

import com.alecbrando.musicplayer.data.remote.ApiService
import com.alecbrando.musicplayer.data.remote.ApiService.Companion.apiInstance
import com.alecbrando.musicplayer.domain.models.SongList
import com.alecbrando.musicplayer.domain.models.SongX
import com.alecbrando.musicplayer.domain.repository.Repository
import com.alecbrando.musicplayer.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiInstance: ApiService
) : Repository {

    override suspend fun getSongList(): Resource<SongX> = withContext(Dispatchers.IO) {
       return@withContext try {
           println("Im Over HERE")
           val res = apiInstance.getSongList()
           if(res.isSuccessful && res.body()!= null){
               Resource.Success(res.body()!!)

           } else{
               Resource.Error("IS BROKEN MA DUDE")
           }
       } catch (e: java.lang.Exception){
           Resource.Error(e.message.toString())
       }
    }

}
