package com.alecbrando.musicplayer.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.alecbrando.musicplayer.domain.datastore.DataStorePreferenceSource
import com.alecbrando.musicplayer.domain.datastore.PreferencesKeys
import com.alecbrando.musicplayer.util.Constant
import com.alecbrando.musicplayer.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(
    name = Constant.DATASTORE_PREFERENCES
)

class DataStoreImpl(private val context:Context): DataStorePreferenceSource {
    override fun getDataStore(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.VIEWED_INFO_SCREEN] ?: false
        }
    }

    override suspend fun setDataStore(viewScreen: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.VIEWED_INFO_SCREEN] = viewScreen
        }
    }

}