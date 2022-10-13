package com.alecbrando.musicplayer.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.alecbrando.musicplayer.domain.datastore.DataStorePrefSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

//Preference Name
const val PREFERENCE_NAME = "MyDataStore"

//Instance of DataStore
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)


class DataStorePrefImpl @Inject constructor(
    private val context: Context
) : DataStorePrefSource {
    override fun getPreferenceInfo(): Flow<Boolean> {
        return context.dataStore.data.map { pref ->
            pref[booleanPreferencesKey("viewscreen")] ?: false
        }
    }

    override suspend fun setPreferenceInfo(viewedInfoScreen: Boolean) {
        context.dataStore.edit { pref ->
            pref[booleanPreferencesKey("viewscreen")] = viewedInfoScreen
        }
    }
}


