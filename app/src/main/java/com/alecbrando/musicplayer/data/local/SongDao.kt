package com.alecbrando.musicplayer.data.local

import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.REPLACE
import com.alecbrando.musicplayer.data.model.Song
import kotlinx.coroutines.flow.Flow



@Dao
interface SongDao {
    @Query("SELECT * FROM songs")
    fun getSongs(): Flow<List<Song>>

    @Insert(onConflict = REPLACE)
    fun insertSong (song:Song)

    @Delete
    fun deleteSong(song:Song)

}