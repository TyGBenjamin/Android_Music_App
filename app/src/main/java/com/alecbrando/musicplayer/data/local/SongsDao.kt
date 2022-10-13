package com.alecbrando.musicplayer.data.local

import androidx.room.*
import com.alecbrando.musicplayer.data.typeconverters.TypeConvertersHelper
import com.alecbrando.musicplayer.domains.models.Songs
import com.alecbrando.musicplayer.domains.models.SongsWrapper
import com.alecbrando.musicplayer.resources.Resource
import kotlinx.coroutines.flow.Flow

@Dao
interface SongsDao {
    @Query("SELECT * FROM songs")
    fun getAllSongs(): Flow<Songs>

}