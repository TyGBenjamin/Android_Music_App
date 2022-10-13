package com.alecbrando.musicplayer.data.local

import androidx.room.*
import com.alecbrando.musicplayer.domains.models.SongsWrapper
import com.alecbrando.musicplayer.resources.Resource
import kotlinx.coroutines.flow.Flow

@Dao
interface SongsDao {
//    @Query("SELECT * FROM songsDatabase")
//    suspend fun getAllSongs(): Resource<SongsWrapper>
//
//    @Query("SELECT * FROM notes WHERE id IN (:id)")
//    suspend fun getNoteById(id: Int): Note
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertNote(note: Note)
//
//    @Delete
//    suspend fun deleteNote(note: Note)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun editNote(note: Note)
//
//    @Query("SELECT * FROM notes WHERE title LIKE (:name)")
//    fun searchNoteByName(name: String): Flow<List<Note>>
}