package com.alecbrando.musicplayer.presentation.fragment

import android.annotation.SuppressLint
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
import coil.load
import com.alecbrando.musicplayer.R
import com.alecbrando.musicplayer.databinding.FragmentDashboardBinding
import com.alecbrando.musicplayer.databinding.SongBinding
import com.alecbrando.musicplayer.domain.models.Song
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
    private var songList: List<SongX>? = null
    private var songIndex : Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentDashboardBinding.inflate(inflater, container, false).also { _binding = it }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setUpMenu()
        initListeners()
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
                    R.id.Metal ->{topViewModel.getGenre("metal")}
                    R.id.hip_hop ->{topViewModel.getGenre("hip_hop")}
                    R.id.country->{topViewModel.getGenre("county")}
                    R.id.edm ->{topViewModel.getGenre("edm")}
                    R.id.Random ->{topViewModel.getGenre("random")}
                    R.id.Reggae ->{topViewModel.getGenre("reggae")}
                    R.id.jazz ->{topViewModel.getGenre("jazz")}
                    R.id.synthWave ->{topViewModel.getGenre("synthwave")}
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

    private fun initListeners() = with(binding){
        btnPlay.setOnClickListener{
            player.start()
            btnPlay.visibility = View.GONE
            btnPause.visibility = View.VISIBLE
        }

        btnPause.setOnClickListener{
            if(player.isPlaying) {
                player.pause()
                btnPause.visibility = View.GONE
                btnPlay.visibility = View.VISIBLE
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




    private fun playSong(songDeets:SongX, mp3: String? = null, song: List<SongX>? = null ) = with(binding) {

        stopMedia()
        if(!mp3.isNullOrEmpty()){
            songList = song
            for(i in songList!!.indices){
                println(songList!!.indices.toString() + "Over Here")
                if(songList?.get(i)?.mp3 == mp3){
                    songIndex = i
                    println( "${songIndex} ")
                    btnNext.setOnClickListener{
                        if(songIndex >= 9){
                           songIndex = 0
                            songIndex = songIndex
                            songPlaying.text = song?.get(songIndex)?.name
                            artistPlaying.text = song?.get(songIndex)?.artist
                            imagePlaying.load(song?.get(songIndex)?.albumPicture)

                            println("$songIndex new index")
                            val nextSong: String? = song?.get(songIndex)?.mp3

                            if (nextSong != null) {
                                nextMedia(nextSong)
                            }
                        }

                        else {
                            songIndex = songIndex + 1
                            println("$songIndex new index")
                            songPlaying.text = song?.get(songIndex)?.name
                            artistPlaying.text = song?.get(songIndex)?.artist
                            imagePlaying.load(song?.get(songIndex)?.albumPicture)
                            val nextSong: String? = song?.get(songIndex)?.mp3


                            if (nextSong != null) {
                                nextMedia(nextSong)
                            }
                        }

                    }

                }




            }
            playerbar.visibility = View.VISIBLE
            songPlaying.text = songDeets.name
            artistPlaying.text = songDeets.artist
            imagePlaying.load(songDeets.albumPicture)
//            player.setAudioStreamType(AudioManager.STREAM_MUSIC)
            try {
                player.setDataSource(mp3)
                player.prepare()
                player.start()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            Log.d("Music", "It's playing${songDeets.name}")

        }
    }

    fun nextMedia(mp3:String){
        if(player.isPlaying) {
            player.stop()
            player.reset()
            player.setDataSource(mp3)
            player.prepare()
            player.start()
        }

    }

    private fun stopMedia() {
        if(player.isPlaying) {
            player.stop()
            player.reset()
        }
    }
}