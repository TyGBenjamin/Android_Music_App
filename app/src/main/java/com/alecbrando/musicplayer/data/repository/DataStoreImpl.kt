package com.alecbrando.musicplayer.data.repository

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.alecbrando.musicplayer.domain.datastore.DataStorePrefSource
import com.alecbrando.musicplayer.util.Constants
import kotlinx.coroutines.flow.Flow

private val Context.dataStore by preferencesDataStore(
    name = Constants.PREFERENCES
)

class DataStoreImpl(private val context: Context) : DataStorePrefSource{
    override fun getPreferenceInfo(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun setPreferenceInfo(viewedInfoScreen: Boolean) {
        TODO("Not yet implemented")
    }
}