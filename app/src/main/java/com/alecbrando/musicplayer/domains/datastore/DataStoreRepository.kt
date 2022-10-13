package com.alecbrando.musicplayer.domains.datastore

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    fun getDataStore(): Flow<Boolean>
    suspend fun setDataStore(viewScreen: Boolean)
}