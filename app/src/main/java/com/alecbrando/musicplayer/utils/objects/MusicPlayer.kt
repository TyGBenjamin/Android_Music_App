package com.alecbrando.musicplayer.utils.objects

import android.content.ContentValues.TAG
import android.media.MediaPlayer
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.Toast
import coil.load
import com.alecbrando.musicplayer.R
import com.alecbrando.musicplayer.databinding.FragmentDashboardBinding
import com.alecbrando.musicplayer.domains.models.Songs

object MusicPlayer{
    private val mediaPlayer : MediaPlayer = MediaPlayer()
    private lateinit var binding : FragmentDashboardBinding
    private var songsList : List<Songs>? = null
    private var songIndex : Int = 0

    fun setSongInfo(mp3: String, songs: MutableList<Songs>, binding: FragmentDashboardBinding) {
        if(mediaPlayer.isPlaying){
            mediaPlayer.stop()
            mediaPlayer.reset()
        } else{
            mediaPlayer.reset()
        }
        songsList = songs
        MusicPlayer.binding = binding
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
            controlSong()
        } catch(e: Exception){
            e.message
        }
    }

    fun stopSong() = with(binding){
        mediaPlayer.stop()
        mediaPlayer.reset()
        llMediaPlayer.visibility = View.GONE
    }

    fun playOrPauseSong() = with(binding){
        if(mediaPlayer.isPlaying){
            btnPlay.setBackgroundResource(com.alecbrando.musicplayer.R.drawable.ic_baseline_play_circle_24)
            mediaPlayer.pause()
        } else{
            btnPlay.setBackgroundResource(com.alecbrando.musicplayer.R.drawable.ic_baseline_pause_circle_outline_24)
            mediaPlayer.start()
        }
    }

    fun fastForwardSong() = with(binding){
        if(mediaPlayer.isPlaying){
            mediaPlayer.stop()
            mediaPlayer.reset()
        } else{
            mediaPlayer.reset()
        }
        binding.btnPlay.setBackgroundResource(com.alecbrando.musicplayer.R.drawable.ic_baseline_pause_circle_outline_24)
        if (songIndex < songsList!!.size-1){
            try{
                llMediaPlayer.visibility = android.view.View.VISIBLE
                mediaPlayerImage.load((songsList as MutableList<Songs>).get(songIndex +1).albumPicture)
                mediaPlayerName.text = (songsList as MutableList<Songs>).get(songIndex +1).name
                mediaPlayer.setDataSource((songsList as MutableList<Songs>).get(songIndex +1).mp3)
                mediaPlayer.prepare()
                mediaPlayer.start()

                songIndex++
            } catch(e: Exception){
                e.message
            }
        } else {
            try {
                mediaPlayerImage.load((songsList as MutableList<Songs>).get(0).albumPicture)
                mediaPlayerName.text = (songsList as MutableList<Songs>).get(0).name
                mediaPlayer.setDataSource((songsList as MutableList<Songs>).get(0).mp3)
                mediaPlayer.prepare()
                mediaPlayer.start()
                songIndex = 0
            } catch (e: Exception) {
                e.message
            }
            val toast: Toast = android.widget.Toast.makeText(
                binding.btnPlay.context,
                "You are at the end of the list, replay from the start...",
                android.widget.Toast.LENGTH_LONG
            )
            toast.show()
        }
    }

    private fun controlSong(){
        val progressBar = binding.progressIndicator
        initializeSeekbar()
        progressBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser){
                    mediaPlayer.seekTo(progress)
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
    private fun initializeSeekbar(){
        binding.progressIndicator.max = mediaPlayer.duration
        val handle = Handler()
        handle.postDelayed(object: Runnable{
            override fun run(){
                try{
                binding.progressIndicator.progress = mediaPlayer.currentPosition
                handle.postDelayed(this, 1000)
                }catch(e: Exception){
                    binding.progressIndicator.progress = 0
                }
            }
        }, 0)
    }
}