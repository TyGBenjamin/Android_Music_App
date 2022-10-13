package com.alecbrando.musicplayer.presentation.fragment

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.Gravity.apply
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.alecbrando.musicplayer.R
import com.alecbrando.musicplayer.databinding.FragmentDashboardBinding
import com.alecbrando.musicplayer.databinding.SongBinding
import com.alecbrando.musicplayer.domain.models.SongX
import com.alecbrando.musicplayer.presentation.adapter.DashboardAdapter
import com.alecbrando.musicplayer.presentation.adapter.TopRecyclerViewAdapter
import com.alecbrando.musicplayer.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.FieldPosition


@AndroidEntryPoint
class Dashboard : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding: FragmentDashboardBinding get() = _binding!!
    private val viewModel by viewModels<DashboardViewModel>()
    private val topViewModel by viewModels<TopRecyclerViewModel>()
    // DashboardAdapter(::playSong. viewModel::setCurrentSong)
    private val dashboardAdapter by lazy { DashboardAdapter(::playSong) }
    private val topRecyclerViewAdapter by lazy { TopRecyclerViewAdapter(::playSong) }
    private val player = MediaPlayer()
    private lateinit var songList: List<SongX>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentDashboardBinding.inflate(inflater, container, false).also { _binding = it }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setUpMenu()
    }
    private fun setUpMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onPrepareMenu(menu: Menu) {
                // Handle for example visibility of menu items
            }
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.Metal ->{}
                    R.id.hip_hop ->{}
                    R.id.country->{}
                    R.id.edm ->{}
                    R.id.Random ->{}
                    R.id.Reggae ->{}
                    R.id.jazz ->{}
                    R.id.synthWave ->{}
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun initViews() = with(binding) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.Songs.collectLatest { songList ->
                    when (songList) {
                        is Resource.Error -> songList.message
                        is Resource.Loading -> Log.d("Loading", "IM LOADING")
                        is Resource.Success ->
                            recyclerViewbottom.adapter = dashboardAdapter.apply {
                                addSongs(songList.data.songs)
                            }
                    }
                }
                // set up current song state flow collect and populate song title and other shit
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                topViewModel.artist.collectLatest { songList ->
                    when (songList) {
                        is Resource.Error -> songList.message
                        is Resource.Loading -> Log.d("Loading", "IM LOADING")
                        is Resource.Success ->
                            recyclerviewTop.adapter = topRecyclerViewAdapter.apply {
                                addSongs(songList.data.songs)
                            }
                    }
                }
            }
        }
    }

    private fun playSong(mp3: String? = null, song: List<SongX>? = null ) = with(binding) {
        if(!mp3.isNullOrEmpty()){
            playerbar.visibility = View.VISIBLE
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
        btnPlay.setOnClickListener{
            player.start()
            btnPause.visibility = View.VISIBLE
        }

        btnPause.setOnClickListener{
            if(player.isPlaying) {
                player.pause()
                btnPause.visibility = View.GONE
            }
        }

        btnStop.setOnClickListener{
            if(player.isPlaying){
                player.stop()
                player.reset()
                playerbar.visibility = View.GONE
            }
        }
    }
}