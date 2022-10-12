package com.alecbrando.musicplayer.domain.datastore

import kotlinx.coroutines.flow.Flow

interface DataStorePrefSource {
    fun getPreferenceInfo() : Flow<Boolean>
    suspend fun setPreferenceInfo(viewedInfoScreen: Boolean)
}