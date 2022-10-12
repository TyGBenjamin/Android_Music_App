package com.alecbrando.musicplayer.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alecbrando.musicplayer.data.repository.RepositoryImpl
import com.alecbrando.musicplayer.domain.model.SongList
import com.alecbrando.musicplayer.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GridViewModel @Inject constructor(private val repo: RepositoryImpl) : ViewModel() {

    private val _songList: MutableStateFlow<Resource<SongList>> = MutableStateFlow(Resource.Loading)
    val songList = _songList.asStateFlow()

    init {
        viewModelScope.launch { _songList.value = repo.getGenre("reggae") }
    }
}
