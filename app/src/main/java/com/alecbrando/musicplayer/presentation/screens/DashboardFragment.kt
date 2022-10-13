package com.alecbrando.musicplayer.presentation.screens

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import com.alecbrando.musicplayer.R
import com.alecbrando.musicplayer.adapters.GridViewAdapter
import com.alecbrando.musicplayer.adapters.ListViewAdapter
import com.alecbrando.musicplayer.databinding.FragmentDashboardBinding
import com.alecbrando.musicplayer.domain.model.Song
import com.alecbrando.musicplayer.util.Constants.TAG
import com.alecbrando.musicplayer.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding: FragmentDashboardBinding get() = _binding!!
    private val viewModel by viewModels<ListViewModel>()
    private val viewModelGrid by viewModels<GridViewModel>()
    private val gridViewAdapter by lazy { GridViewAdapter(::playSong) }
    private val listViewAdapter by lazy { ListViewAdapter(::playSong) }
    private val player = MediaPlayer()
    private lateinit var songList: List<Song>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = FragmentDashboardBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setUpMenu()
//
        initViews()
        initListeners()
        setUpMenu()


    }

    private fun setUpMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onPrepareMenu(menu: Menu) {
                // Handle for example visibility of menu items
            }

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.drop_down, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                when (menuItem.itemId) {
                    R.id.hip_hop -> {viewModelGrid.getGenre("hip_hop")}
                    R.id.Metal -> {viewModelGrid.getGenre("metal")}
                    R.id.Random -> {viewModelGrid.getGenre("random")}
                    R.id.Reggae -> {viewModelGrid.getGenre("reggae")}
                    R.id.synthWave -> {viewModelGrid.getGenre("synthwave")}
                    R.id.jazz -> {viewModelGrid.getGenre("jazz")}
                    R.id.edm -> {viewModelGrid.getGenre("edm")}

                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun initListeners() = with(binding) {
        btnPause.setOnClickListener { player.pause() }
        btnPlay.setOnClickListener { player.start() }
        btnStop.setOnClickListener {
            stopMedia()
            navBarView.visibility = View.GONE
        }
        btnNext.setOnClickListener{

        }
    }

    private fun initViews() = with(binding) {
        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
            listOf(
                launch {
                    viewModel.songList.collectLatest { songState ->
                        when (songState) {
                            is Resource.Error -> Log.d(TAG, "error: ${songState.message}")
                            is Resource.Loading -> Log.d("LOADING", "loading...")
                            is Resource.Success -> {
                                Log.d(TAG, "success: ${songState.data.songs}")
                                rvView2.adapter = listViewAdapter.apply {
                                    addItems(songState.data.songs)
                                }
                            }
                        }
                    }
                },
                launch {
                    viewModelGrid.songList.collectLatest { gridState ->
                        when (gridState) {
                            is Resource.Error -> Log.d(TAG, "error: ${gridState.message}")
                            is Resource.Loading -> Log.d("LOADING", "loading...")
                            is Resource.Success -> {
                                Log.d(TAG, "success: ${gridState.data.songs}")
                                rvView.adapter = gridViewAdapter.apply {
                                    addItems(gridState.data.songs)
                                }
                            }
                        }
                    }
                }
            ).joinAll()

//                }
        }

    }

    private fun playSong(song: Song, songlist: List<Song>? = null, position: Int) = with(binding) {
        stopMedia()
        if(song.mp3.isNotEmpty()) {
//            stopMedia()
            navBarView.visibility = View.VISIBLE
            artistiPlaying.text = song.artist
            songPlaying.text = song.name
            albumPlayer.load(song.albumPicture)
            loadMedia(song.mp3)
        }
    }

    private fun loadMedia(mp3: String) {
        player.setAudioStreamType(AudioManager.STREAM_MUSIC)
        try {
            player.setDataSource(mp3)
            player.prepare()
            player.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun stopMedia() {
        if (player.isPlaying) {
            player.stop()
            player.reset()
        }
    }
}

/**
 * DashboardFragment
 * @author Tyler Gandy 2022-09-21
 */