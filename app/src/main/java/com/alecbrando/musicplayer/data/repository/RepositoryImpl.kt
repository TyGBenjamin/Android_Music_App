package com.alecbrando.musicplayer.data.repository

import com.alecbrando.musicplayer.data.service.ApiService
import com.alecbrando.musicplayer.domains.models.SongsWrapper
import com.alecbrando.musicplayer.domains.repository.Repository
import com.alecbrando.musicplayer.resources.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: ApiService): Repository {
    override suspend fun getAllSongs(): Resource<SongsWrapper> = withContext(Dispatchers.IO){
        return@withContext try{
            val res = apiService.getAllSongs()
            if(res.isSuccessful && res.body() != null){
                Resource.Success(res.body()!!)
            } else{
                Resource.Error("Something went wrong")
            }
        } catch(e: Exception){
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun getSongsByGenres(genre: String): Resource<SongsWrapper> = withContext(Dispatchers.IO) {
        return@withContext try{
            val res = apiService.getSongsByGenres(genre)
            if(res.isSuccessful && res.body() != null){
                Resource.Success(res.body()!!)
            } else{
                Resource.Error("Something went wrong")
            }
        } catch(e: Exception){
            Resource.Error(e.message.toString())
        }
    }

}