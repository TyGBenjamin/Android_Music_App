package com.alecbrando.musicplayer.utils

import android.content.ContentValues.TAG
import android.media.AudioManager
import android.media.MediaPlayer
import android.util.Log
import androidx.fragment.app.Fragment
import com.alecbrando.musicplayer.domains.models.Songs

fun Fragment.playMusic(
    mp3: String? = null,
    songList: List<Songs>? = null
) {
    var songMp3 : String
    var songs : List<Songs>?
    var index : Int
    var mediaPlayer = MediaPlayer()
    if(!mp3.isNullOrEmpty()){
        songMp3 = mp3
        songs = songList
        for(i in songList!!.indices){
            if(songs?.get(i)?.mp3 == songMp3){
                index = i
            }
        }

        if(mediaPlayer.isPlaying){
            mediaPlayer.stop()
            mediaPlayer.reset()
            mediaPlayer.release()
        }

        mediaPlayer = MediaPlayer()
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)

        try{


            mediaPlayer.prepare()
            mediaPlayer.start()

        } catch(e: Exception){
            e.message
        }

    }

    var i : Int
//    val bo = songList?.find { it.name == mp3 }}

    for(i in songList!!.indices){
//        if(songList.get(i).mp3 == songMp3){
//
//        }
    }






}