package com.alecbrando.musicplayer.domains.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.alecbrando.musicplayer.utils.objects.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(
    name = Constants.DATASTORE_PREFERENCES
)

class DataStoreRepositoryImpl(private val context: Context): DataStoreRepository {
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