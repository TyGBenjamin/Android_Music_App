package com.alecbrando.musicplayer.paging3mediator

import androidx.datastore.preferences.PreferencesProto
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import com.alecbrando.musicplayer.data.local.SongsDao
import com.alecbrando.musicplayer.data.local.SongsDatabase
import com.alecbrando.musicplayer.data.repository.RepositoryImpl
import com.alecbrando.musicplayer.data.service.ApiService
import com.alecbrando.musicplayer.domains.models.SongsWrapper
import java.security.Key
//
//@OptIn(ExperimentalPagingApi::class)
//class ExampleRemoteMediator(
//    private val query: String,
//    private val database: SongsDatabase,
//    private val networkService: ApiService
//) : MusicRemoteMediator(){
//    val songsDao = database.songsDao()
//    override suspend fun load(
//        loadType: LoadType,
//        state: PagingState<Key, PreferencesProto.Value>
//    ): MediatorResult {
//        return
//    }
//}