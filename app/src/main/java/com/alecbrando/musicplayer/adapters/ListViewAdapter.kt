package com.alecbrando.musicplayer.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.alecbrando.musicplayer.databinding.SingleSongBinding
import com.alecbrando.musicplayer.domain.model.Song

class ListViewAdapter : RecyclerView.Adapter<ListViewAdapter.ListViewHolder>() {
    private var songList: List<Song> = mutableListOf()

    class ListViewHolder(
        private val binding: SingleSongBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun applySong(song: Song) = with(binding) {
            imageView.load(song.albumPicture)
            tvTitle.text = song.name
            tvArtist.text = song.artist
            println("apply")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = SingleSongBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = songList[position]
        holder.applySong(item)
    }

    override fun getItemCount() = songList.size


    fun addItems(songList: List<Song>) {
        this.songList = songList
    }
}