package com.alecbrando.musicplayer.data.repository

import com.alecbrando.musicplayer.data.remote.ApiService
import com.alecbrando.musicplayer.domain.models.SongListWrapper
import com.alecbrando.musicplayer.domain.repository.Repository
import com.alecbrando.musicplayer.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor() : Repository {
    private val apiInstance by lazy { ApiService.apiInstance }

    override suspend fun getSongs(): Resource<SongListWrapper> = withContext(Dispatchers.IO) {
        return@withContext try {
            val res = apiInstance.getSongs()
            if (res.isSuccessful && res.body() != null) {
                Resource.Success(res.body()!!)
            } else {
                Resource.Error("Someting Wong")
            }
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun getSongsByPage(page: Int): Resource<SongListWrapper> = withContext(Dispatchers.IO) {
        return@withContext try {
            val res = apiInstance.getSongsByPage(page)
            if (res.isSuccessful && res.body() != null) {
                Resource.Success(res.body()!!)
            } else {
                Resource.Error("Someting Wong")
            }
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun getSongsByGenre(genre: String): Resource<SongListWrapper> = withContext(Dispatchers.IO) {
        return@withContext try {
            val res = apiInstance.getSongsByGenre(genre)
            if (res.isSuccessful && res.body() != null) {
                Resource.Success(res.body()!!)
            } else {
                Resource.Error("Someting Wong")
            }
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }
}