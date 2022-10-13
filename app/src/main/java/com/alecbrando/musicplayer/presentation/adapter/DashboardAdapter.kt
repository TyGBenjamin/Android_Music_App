package com.alecbrando.musicplayer.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.alecbrando.musicplayer.databinding.FragmentDashboardBinding
import com.alecbrando.musicplayer.databinding.SongBinding
import com.alecbrando.musicplayer.domain.models.Song
import com.alecbrando.musicplayer.domain.models.SongList
import com.alecbrando.musicplayer.domain.models.SongX


class DashboardAdapter(
    private val playSong:(mp3: String, songs: List<SongX>) -> Unit,
    // cb function to set current song (Song) -> Unit
):RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder>() {
    private lateinit var songs: List<SongX>

    inner class DashboardViewHolder(
        private val binding: SongBinding,
        private var binding2: FragmentDashboardBinding?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun apply(song: SongX) = with(binding) {
            Log.d("Logger", "displaySongs: ${song.name}")
            imageView.load(song.albumPicture)
            tvTitle.text = song.name
            tvArtist.text = song.artist



            root.setOnClickListener{
                println("Song Clicked ${song.name}")
                // call cb function to set current song
                playSong(song.mp3, songs)
                binding2?.songPlaying?.text = song.name
                binding2?.artistPlaying?.text = song.artist
                binding2?.imagePlaying?.load(song.albumPicture)

            }






        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
    DashboardViewHolder{
        val binding = SongBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DashboardViewHolder(binding,null)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int)   {
        val currentItem = songs[position]
        holder.apply(currentItem)
    }

    override fun getItemCount(): Int {
        return this.songs.size
    }

    fun addSongs(song: List<SongX>) {
        this.songs = song
    }

}