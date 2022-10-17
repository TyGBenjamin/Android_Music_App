package com.alecbrando.musicplayer.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.alecbrando.musicplayer.R
import com.alecbrando.musicplayer.databinding.FragmentDashboardBinding
import com.alecbrando.musicplayer.domains.models.Songs
import com.alecbrando.musicplayer.presentation.adapters.DashboardAdapter
import com.alecbrando.musicplayer.presentation.adapters.DashboardAdapterBottom
import com.alecbrando.musicplayer.presentation.viewmodels.DashboardViewModel
import com.alecbrando.musicplayer.presentation.viewmodels.DashboardViewModelBottom
import com.alecbrando.musicplayer.resources.Resource
import com.alecbrando.musicplayer.utils.objects.MusicPlayer
import com.alecbrando.musicplayer.utils.collectLatestLifecycleFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding: FragmentDashboardBinding get() = _binding!!
    private val viewModel by viewModels<DashboardViewModel>()
    private val viewModelBottom by viewModels<DashboardViewModelBottom>()
    private val dashboardAdapter by lazy { DashboardAdapter(::setSongInfo) }
    private val dashboardAdapterBottom by lazy { DashboardAdapterBottom(::setSongInfo) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentDashboardBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    private fun initViews() = with(binding) {
        collectLatestLifecycleFlow(viewModel.songList){ songList ->
            when (songList) {
                is Resource.Idle -> {}
                is Resource.Error -> {}
                is Resource.Loading -> {}
                is Resource.Success ->
                    rvDashboardRecyclerView.adapter = dashboardAdapter.apply {
                        addSongs(songList.data.songs)
                    }
            }
        }
        collectLatestLifecycleFlow(viewModelBottom.songList){ songList ->
            when (songList) {
                is Resource.Idle -> {}
                is Resource.Error -> {}
                is Resource.Loading -> {}
                is Resource.Success ->
                    rvDashboardRecyclerViewBottom.adapter = dashboardAdapterBottom.apply {
                        addSongs(songList.data.songs)
                    }
            }
        }
    }

    private fun initListeners() = with(binding) {
        binding.btnSearch.setOnClickListener {
            searchMenu()
        }
        binding.btnStop.setOnClickListener {
            MusicPlayer.stopSong()
        }
        binding.btnPlay.setOnClickListener {
            MusicPlayer.playOrPauseSong()
        }
        binding.btnForward.setOnClickListener {
            MusicPlayer.fastForwardSong()
        }
    }

    private fun searchMenu() = with(binding) {
        val popup = PopupMenu(context, this.btnSearch)
        popup.inflate(R.menu.search_menu)
        popup.setOnMenuItemClickListener{ item ->
            when(item.itemId){
                R.id.mi_country -> {
                    viewModel.getSongByGenre("country")
                    true
                }
                R.id.mi_hip_hop -> {
                    viewModel.getSongByGenre("hip_hop")
                    true
                }
                R.id.mi_edm -> {
                    viewModel.getSongByGenre("edm")
                    true
                }
                R.id.mi_random -> {
                    viewModel.getSongByGenre("random")
                    true
                }
                R.id.mi_jazz -> {
                    viewModel.getSongByGenre("jazz")
                    true
                }
                R.id.mi_metal -> {
                    viewModel.getSongByGenre("metal")
                    true
                }
                R.id.mi_reggae -> {
                    viewModel.getSongByGenre("reggae")
                    true
                }
                R.id.mi_synthwave -> {
                    viewModel.getSongByGenre("synthwave")
                    true
                }
                else -> false
            }
        }
        popup.show()
    }

    private fun setSongInfo(mp3: String, songs: MutableList<Songs>){
        MusicPlayer.setSongInfo(mp3, songs, binding)
    }
}