package com.alecbrando.musicplayer.presentation.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alecbrando.musicplayer.data.repository.RepositoryImpl
import com.alecbrando.musicplayer.domain.models.SongList
import com.alecbrando.musicplayer.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopRecyclerViewModel @Inject constructor(private val repo : RepositoryImpl): ViewModel() {

    private val _artist: MutableStateFlow<Resource<SongList>> = MutableStateFlow(Resource.Loading)
    val artist = _artist.asStateFlow()

    init {
        getGenre("jazz")
    }


    fun getGenre(genre: String)= viewModelScope.launch{
        _artist.value = repo.getGenres(genre)

    }

}