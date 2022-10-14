package com.alecbrando.musicplayer.presentation.fragments

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.alecbrando.musicplayer.R
import com.alecbrando.musicplayer.databinding.FragmentDashboardBinding
import com.alecbrando.musicplayer.domains.models.Songs
import com.alecbrando.musicplayer.presentation.adapters.DashboardAdapter
import com.alecbrando.musicplayer.presentation.adapters.DashboardAdapterBottom
import com.alecbrando.musicplayer.presentation.viewmodels.DashboardViewModel
import com.alecbrando.musicplayer.presentation.viewmodels.DashboardViewModelBottom
import com.alecbrando.musicplayer.resources.Resource
import com.alecbrando.musicplayer.utils.MediaPlayerObj
import com.alecbrando.musicplayer.utils.collectLatestLifecycleFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding: FragmentDashboardBinding get() = _binding!!
    private val viewModel by viewModels<DashboardViewModel>()
    private val viewModelBottom by viewModels<DashboardViewModelBottom>()
    private val dashboardAdapter by lazy { DashboardAdapter(::playOrPauseSong) }
    private val dashboardAdapterBottom by lazy { DashboardAdapterBottom(::playOrPauseSong) }
    private var songsList : List<Songs>? = null
    private var songIndex : Int = 0
    private var mediaPlayer : MediaPlayer = MediaPlayerObj.mediaPlayer

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
            stopSong()
        }
        binding.btnPlay.setOnClickListener {
            fastForwardToNextSong()
        }
        binding.btnForward.setOnClickListener {
            playOrPauseSong()
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
    private fun fastForwardToNextSong() = with(binding){
//        var length = mediaPlayer.currentPosition
        if(mediaPlayer.isPlaying){
            mediaPlayer.pause()
            binding.btnPlay.setBackgroundResource(R.drawable.ic_baseline_play_circle_24)
        } else{
//            mediaPlayer.seekTo(length)
            mediaPlayer.start()
            binding.btnPlay.setBackgroundResource(R.drawable.ic_baseline_pause_circle_outline_24)
        }
    }
    private fun stopSong() = with(binding){
        if(mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.reset()
//            mediaPlayer.release()
            llMediaPlayer.visibility = View.GONE
        }
    }
    private fun playOrPauseSong(mp3: String? = null, songs: MutableList<Songs>? = null){
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)

        if(mediaPlayer.isPlaying){
            mediaPlayer.stop()
            mediaPlayer.reset()
//            mediaPlayer.release()
        }

        if(!mp3.isNullOrEmpty()){
            songsList = songs
            for(i in songsList!!.indices){
                if(songs?.get(i)?.mp3 == mp3){
                    songIndex = i
                }
            }
            try{
                binding.llMediaPlayer.visibility = View.VISIBLE
                binding.btnPlay.setBackgroundResource(R.drawable.ic_baseline_pause_circle_outline_24)
                binding.mediaPlayerImage.load((songsList as MutableList<Songs>).get(songIndex).albumPicture)
                binding.mediaPlayerName.text = (songsList as MutableList<Songs>).get(songIndex).name
                mediaPlayer.setDataSource((songsList as MutableList<Songs>).get(songIndex).mp3)
                mediaPlayer.prepare()
                mediaPlayer.start()
            } catch(e: Exception){
                e.message
            }
        }
        else{
            if (songIndex < songsList!!.size-1){
                try{
                    binding.llMediaPlayer.visibility = View.VISIBLE
                    binding.mediaPlayerImage.load((songsList as MutableList<Songs>).get(songIndex+1).albumPicture)
                    binding.mediaPlayerName.text = (songsList as MutableList<Songs>).get(songIndex+1).name
                    mediaPlayer.setDataSource((songsList as MutableList<Songs>).get(songIndex+1).mp3)
                    mediaPlayer.prepare()
                    mediaPlayer.start()
                    songIndex++
                } catch(e: Exception){
                    e.message
                }
            } else{
                try{
                    binding.mediaPlayerImage.load((songsList as MutableList<Songs>).get(0).albumPicture)
                    binding.mediaPlayerName.text = (songsList as MutableList<Songs>).get(0).name
                    mediaPlayer.setDataSource((songsList as MutableList<Songs>).get(0).mp3)
                    mediaPlayer.prepare()
                    mediaPlayer.start()
                    songIndex = 0
                } catch(e: Exception){
                    e.message
                }
                val toast : Toast = Toast.makeText(context, "You are at the end of the list, replay from the start...", Toast.LENGTH_LONG)
                toast.show()
            }
        }
    }


//    private fun playOrPauseSong(mp3: String? = null, songs: MutableList<Songs>? = null) = with(binding){
//        playOrPause(mp3, songs, mediaPlayer, binding)
//    }

}