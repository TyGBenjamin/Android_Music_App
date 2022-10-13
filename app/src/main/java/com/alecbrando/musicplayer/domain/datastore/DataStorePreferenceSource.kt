package com.alecbrando.musicplayer.domain.datastore

import kotlinx.coroutines.flow.Flow

interface DataStorePreferenceSource {
    fun getDataStore(): Flow<Boolean>
    suspend fun setDataStore(viewScreen: Boolean)
}