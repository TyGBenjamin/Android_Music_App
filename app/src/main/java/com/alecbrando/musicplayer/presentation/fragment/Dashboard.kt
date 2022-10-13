package com.alecbrando.musicplayer.presentation.fragment

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.Gravity.apply
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.alecbrando.musicplayer.databinding.FragmentDashboardBinding
import com.alecbrando.musicplayer.databinding.SongBinding
import com.alecbrando.musicplayer.domain.models.SongX
import com.alecbrando.musicplayer.presentation.adapter.DashboardAdapter
import com.alecbrando.musicplayer.presentation.adapter.TopRecyclerViewAdapter
import com.alecbrando.musicplayer.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class Dashboard : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding: FragmentDashboardBinding get() = _binding!!
    private val viewModel by viewModels<DashboardViewModel>()
    private val topViewModel by viewModels<TopRecyclerViewModel>()
    private val dashboardAdapter by lazy { DashboardAdapter(::playSong) }
    private val topRecyclerViewAdapter by lazy{TopRecyclerViewAdapter(::playSong)}
    private val player = MediaPlayer()
    private lateinit var songList : List<SongX>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentDashboardBinding.inflate(inflater, container, false).also { _binding = it }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() = with(binding) {
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.Songs.collectLatest { songList ->
                    when (songList) {
                        is Resource.Error -> songList.message
                        is Resource.Loading -> Log.d("Loading", "IM LOADING")
                        is Resource.Success ->
                            recyclerViewbottom.adapter = dashboardAdapter.apply{
                                addSongs(songList.data.songs)
                            }
                    }
                }
            }
        }
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                topViewModel.artist.collectLatest { songList ->
                    when (songList) {
                        is Resource.Error -> songList.message
                        is Resource.Loading -> Log.d("Loading", "IM LOADING")
                        is Resource.Success ->
                            recyclerviewTop.adapter = topRecyclerViewAdapter.apply{
                                addSongs(songList.data.songs)
                            }
                    }
                }
            }
        }
    }

    private fun playSong(mp3: String? = null, song: List<SongX>? = null)=with(binding){
//        if(!mp3.isNullOrEmpty()){
//
//        }
        btnPlay.setOnClickListener {
            player.setAudioStreamType(AudioManager.STREAM_MUSIC)
            try {
                player.setDataSource(mp3)
                player.prepare()
                player.start()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            Log.d("Music", "It's playing")







    }
}