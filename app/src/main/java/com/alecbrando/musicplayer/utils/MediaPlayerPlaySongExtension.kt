package com.alecbrando.musicplayer.utils

import android.media.AudioManager
import android.media.MediaPlayer
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import coil.load
import com.alecbrando.musicplayer.R
import com.alecbrando.musicplayer.databinding.FragmentDashboardBinding
import com.alecbrando.musicplayer.domains.models.Songs

private var songsList : List<Songs>? = null
private var songIndex : Int = 0
private var mediaPlayer = MediaPlayer()

fun Fragment.playMusic(
    mp3: String? = null,
    songs: MutableList<Songs>? = null,
    binding: FragmentDashboardBinding
) {
    if(mediaPlayer.isPlaying){
        mediaPlayer.stop()
        mediaPlayer.reset()
        mediaPlayer.release()
    }
    mediaPlayer = MediaPlayer()
    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)

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
    } else{
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
            val toast : Toast = Toast.makeText(context, "You are at the end of the list...", Toast.LENGTH_LONG)
            toast.show()
        }
    }
}