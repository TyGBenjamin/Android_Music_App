package com.alecbrando.musicplayer.presentation.dashboard

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import coil.load
import com.alecbrando.musicplayer.R
import com.alecbrando.musicplayer.databinding.ActivityDashboardBinding
import com.alecbrando.musicplayer.domain.models.Song
import com.alecbrando.musicplayer.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDashboardBinding.inflate(layoutInflater) }
    private val gridViewModel by viewModels<GridViewModel>()
    private val listViewModel by viewModels<ListViewModel>()
    private val gridAdapter by lazy { GridAdapter(@DashboardActivity ::handleThumbnailClick) }
    private val listAdapter by lazy { ListAdapter(@DashboardActivity ::handleThumbnailClick) }
    private var mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
        initListeners()
    }

    private fun initListeners() = with(binding) {
        btnFilter.setOnClickListener { toggleFilter() }
        btnCountry.setOnClickListener { gridViewModel.setSongsByGenre("country") }
        btnHipHop.setOnClickListener { gridViewModel.setSongsByGenre("hip_hop") }
        btnEdm.setOnClickListener { gridViewModel.setSongsByGenre("edm") }
        btnRandom.setOnClickListener { gridViewModel.setSongsByGenre("random") }
        btnJazz.setOnClickListener { gridViewModel.setSongsByGenre("jazz") }
        btnMetal.setOnClickListener { gridViewModel.setSongsByGenre("metal") }
        btnReggae.setOnClickListener { gridViewModel.setSongsByGenre("reggae") }
        btnSynthwave.setOnClickListener { gridViewModel.setSongsByGenre("synthwave") }

        btnPp.setOnClickListener {
            if (layoutCurrentSong.isVisible) {
                if (playToggle) pause()
                else {
                    if (stopped) playSong() else resume()
                }
            }
        }
        btnStop.setOnClickListener { if (layoutCurrentSong.isVisible) stopSong() }
    }

    private fun toggleFilter() = with(binding) {
        if (filterMenu.isVisible) {
            filterMenu.visibility = View.GONE
            btnFilter.setBackgroundResource(R.drawable.ic_filter)
        } else {
            filterMenu.visibility = View.VISIBLE
            btnFilter.setBackgroundResource(R.drawable.ic_filter_close)
        }
    }

    private fun initViews() = with(binding) {
        setUpGridAdapter()
        setUpListAdapter()
    }

    private fun setUpGridAdapter() = with(binding) {
        lifecycleScope.launch {
            gridViewModel.songList.collectLatest { songList ->
                when (songList) {
                    is Resource.Error -> println("${songList.message}")
                    is Resource.Loading -> println("loading...")
                    is Resource.Success ->
                        rvGrid.adapter = gridAdapter.apply {
                            addItems(songList.data.songs)
                        }
                }
            }
        }
    }

    private fun setUpListAdapter() = with(binding) {
        lifecycleScope.launch {
            listViewModel.songList.collectLatest { songList ->
                when (songList) {
                    is Resource.Error -> println("${songList.message}")
                    is Resource.Loading -> println("loading...")
                    is Resource.Success ->
                        rvList.adapter = listAdapter.apply {
                            addItems(songList.data.songs)
                        }
                }
            }
        }
    }

    //==================== Media Control
    private fun handleThumbnailClick(song: Song) = with(binding) {
        stopSong()
        setSong(song)
        tvAuthor.text = song.artist
        tvSong.text = song.name
        ivCurrent.load(song.albumPicture)
        playSong()
    }

    private fun setSong(song: Song) { currentSong = song }

    private fun togglePlay() { playToggle = !playToggle }

    private fun playSong() = with(binding) {
        togglePlay()
        val audioUrl = currentSong?.mp3
        mediaPlayer = MediaPlayer()
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        try {
            mediaPlayer.setDataSource(audioUrl)
            mediaPlayer.prepare()
            mediaPlayer.start()
        } catch (e: Exception) {
            e.message
        }
        layoutCurrentSong.visibility = View.VISIBLE
        btnPp.setBackgroundResource(R.drawable.ic_pause)
        stopped = false
    }

    private fun stopSong() = with(binding) {
        if (mediaPlayer.isPlaying) {
            togglePlay()
            mediaPlayer.stop()
            mediaPlayer.reset()
            mediaPlayer.release()
            btnPp.setBackgroundResource(R.drawable.ic_play)
            stopped = true
        }
    }

    private fun pause() {
        togglePlay()
        mediaPlayer.pause()
        binding.btnPp.setBackgroundResource(R.drawable.ic_play)
    }

    private fun resume() {
        togglePlay()
        mediaPlayer.start()
        binding.btnPp.setBackgroundResource(R.drawable.ic_pause)
        stopped = false
    }

    companion object {
        private var playToggle = false
        private var stopped = false
        private var currentSong: Song? = null
    }

}