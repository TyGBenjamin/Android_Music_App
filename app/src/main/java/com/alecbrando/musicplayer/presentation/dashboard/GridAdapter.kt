package com.alecbrando.musicplayer.presentation.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.alecbrando.musicplayer.databinding.ThumbnailGridBinding
import com.alecbrando.musicplayer.domain.models.Song

class GridAdapter(
    private val handleThumbnailClick: (song: Song) -> Unit
) : RecyclerView.Adapter<GridAdapter.ThumbnailViewHolder>() {
    private var musicList: MutableList<Song> = mutableListOf()

    class ThumbnailViewHolder(
        private val binding: ThumbnailGridBinding,
        private val handleThumbnailClick: (song: Song) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun applyItem(song: Song) = with(binding) {
            thumbnailText.text = song.name
            thumbnailImage.load(song.albumPicture)

            binding.root.setOnClickListener { handleThumbnailClick(song) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThumbnailViewHolder {
        val binding =
            ThumbnailGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ThumbnailViewHolder(binding, handleThumbnailClick)
    }

    override fun onBindViewHolder(holder: ThumbnailViewHolder, position: Int) {
        holder.applyItem(musicList[position])
    }

    override fun getItemCount() = musicList.size

    fun addItems(musicList: List<Song>) {
        this.musicList = musicList as MutableList<Song>
        notifyDataSetChanged()
    }
}
