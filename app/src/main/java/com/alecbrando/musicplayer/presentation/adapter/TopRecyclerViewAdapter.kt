package com.alecbrando.musicplayer.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.alecbrando.musicplayer.databinding.ArtistBinding
import com.alecbrando.musicplayer.databinding.SongBinding
import com.alecbrando.musicplayer.domain.models.SongX

class TopRecyclerViewAdapter: RecyclerView.Adapter<TopRecyclerViewAdapter.TopViewHolder>() {
    private lateinit var genre: List<SongX>

    inner class TopViewHolder(
        private val binding: ArtistBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun apply(songs: SongX) = with(binding) {
            Log.d("Logger", "displaySongs: ${songs.name}")
            imageView2.load(songs.albumPicture)
            tvSong2.text = songs.name
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            TopViewHolder{
        val binding = ArtistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopViewHolder, position: Int)   {
        val currentItem = genre[position]
        holder.apply(currentItem)
    }

    override fun getItemCount(): Int {
        return this.genre.size
    }

    fun addSongs(song: List<SongX>) {
        this.genre = song
    }

}