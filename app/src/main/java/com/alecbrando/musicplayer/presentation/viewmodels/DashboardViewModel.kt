package com.alecbrando.musicplayer.presentation.viewmodels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alecbrando.musicplayer.domains.models.Songs
import com.alecbrando.musicplayer.domains.models.SongsWrapper
import com.alecbrando.musicplayer.domains.repository.Repository
import com.alecbrando.musicplayer.domains.usecases.GetAllSongsUseCase
import com.alecbrando.musicplayer.domains.usecases.GetSongsByGenreUseCase
import com.alecbrando.musicplayer.resources.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/** DashboardViewModel - start of application.
 * @author Ngu Nguyen 10/10/2022
 */
@HiltViewModel
class DashboardViewModel @Inject constructor(
    private  val getSongsByGenreUseCase: GetSongsByGenreUseCase
): ViewModel() {
    private var _songList : MutableStateFlow<Resource<SongsWrapper>> = MutableStateFlow(Resource.Idle)
    val songList = _songList.asStateFlow()

    init{
        getSongByGenre("random")
    }

    fun getSongByGenre(genre: String){
        viewModelScope.launch {
            _songList.value = getSongsByGenreUseCase(genre)
        }
    }
}