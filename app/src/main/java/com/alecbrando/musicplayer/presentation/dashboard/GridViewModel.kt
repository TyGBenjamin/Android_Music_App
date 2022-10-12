package com.alecbrando.musicplayer.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alecbrando.musicplayer.domain.models.SongListWrapper
import com.alecbrando.musicplayer.domain.repository.Repository
import com.alecbrando.musicplayer.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GridViewModel @Inject constructor(
    private val repo: Repository
): ViewModel() {
    private val _songList: MutableStateFlow<Resource<SongListWrapper>> =
        MutableStateFlow(Resource.Loading)
    val songList = _songList.asStateFlow()

    init { setSongsByGenre("random") }

    fun setSongsByGenre(genre: String) {
        viewModelScope.launch { _songList.value = repo.getSongsByGenre(genre) }
    }

}