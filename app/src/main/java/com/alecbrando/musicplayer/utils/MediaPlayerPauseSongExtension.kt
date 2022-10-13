package com.alecbrando.musicplayer.utils

import android.media.MediaPlayer
import androidx.fragment.app.Fragment
import com.alecbrando.musicplayer.R
import com.alecbrando.musicplayer.databinding.FragmentDashboardBinding


fun Fragment.pauseMusic(
    binding: FragmentDashboardBinding
) {
    val mediaPlayer = MediaPlayer()
    var length = mediaPlayer.currentPosition
    if(mediaPlayer.isPlaying){
        mediaPlayer.pause()
        binding.btnPlay.setBackgroundResource(R.drawable.ic_baseline_play_circle_24)
    } else{
        mediaPlayer.seekTo(length)
        mediaPlayer.start()
        binding.btnPlay.setBackgroundResource(R.drawable.ic_baseline_pause_circle_outline_24)
    }
}