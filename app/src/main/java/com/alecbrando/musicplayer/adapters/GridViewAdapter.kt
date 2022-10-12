package com.alecbrando.musicplayer.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.alecbrando.musicplayer.databinding.SingleImageBinding
import com.alecbrando.musicplayer.domain.model.Song

class GridViewAdapter : RecyclerView.Adapter<GridViewAdapter.GridViewHolder>() {
    private var songList: List<Song> = mutableListOf()

    class GridViewHolder(
        private val binding: SingleImageBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun applySong(song: Song) = with(binding) {
            imageView.load(song.albumPicture)
            tvTitle.text = song.artist
            println("apply")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val binding = SingleImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GridViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val item = songList[position]
        holder.applySong(item)
    }

    override fun getItemCount() = songList.size


    fun addItems(songList: List<Song>) {
        this.songList = songList
    }
}