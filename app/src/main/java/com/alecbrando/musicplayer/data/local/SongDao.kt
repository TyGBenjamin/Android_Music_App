package com.alecbrando.musicplayer.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import com.alecbrando.musicplayer.domain.models.SongX
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

@Dao
interface SongDao {

    @androidx.room.Query("SELECT * FROM songs")
    fun getSongs(): Flow<List<SongX>>

    @Insert(onConflict = REPLACE)
    fun insertSong (song:SongX)

    @Delete
    fun deleteSong(song:SongX)

}