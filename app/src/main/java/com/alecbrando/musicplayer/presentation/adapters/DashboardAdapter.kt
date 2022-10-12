package com.alecbrando.musicplayer.presentation.adapters

import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.alecbrando.musicplayer.databinding.DashboardRecyclerViewBinding
import com.alecbrando.musicplayer.domains.models.Songs

class DashboardAdapter : RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder>() {
    private var songs: MutableList<Songs> = mutableListOf()

    inner class DashboardViewHolder(
        private val binding: DashboardRecyclerViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun displaySongs(songs: Songs) = with(binding) {
            ivSong.load(songs.albumPicture)
            tvSongTitle.text = songs.name
            root.setOnClickListener{
                playSong(songs.mp3, binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val binding = DashboardRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DashboardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val song = songs[position]
        Log.d("Logger", "onBindViewHolder: ${song}")
        holder.displaySongs(song)
    }

    override fun getItemCount(): Int = songs.size

    fun addSongs(songs: List<Songs>) {
        if (songs.isEmpty()) {
            this.songs = mutableListOf()
        } else {
            Log.d("Logger", "addSongs: $songs")
            this.songs = songs as MutableList<Songs>
        }
        notifyDataSetChanged()
    }

    private fun playSong(mp3: String, binding: DashboardRecyclerViewBinding){
        val mediaPlayer = MediaPlayer()
        try{
            mediaPlayer.stop()

        } catch(e: Exception){

        }
        mediaPlayer.setDataSource(binding.root.context, Uri.parse(mp3))
        mediaPlayer.prepare()
        mediaPlayer.start()


    }

}