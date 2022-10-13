package com.alecbrando.musicplayer.presentation.fragment

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
class DashboardViewModel @Inject constructor(private val repo : RepositoryImpl): ViewModel() {

    private val _Song: MutableStateFlow<Resource<SongList>> = MutableStateFlow(Resource.Loading)
    val Songs = _Song.asStateFlow()

    init {
        getSongList()
    }


    fun getSongList() {
        viewModelScope.launch {
            _Song.value = repo.getSongList()
        }
    }

}