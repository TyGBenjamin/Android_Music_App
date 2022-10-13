package com.alecbrando.musicplayer.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.alecbrando.musicplayer.databinding.FragmentDashboardBinding
import com.alecbrando.musicplayer.databinding.SingleImageBinding
import com.alecbrando.musicplayer.domain.model.Song

class GridViewAdapter(
    private val playSong :(mp3: String, songs: List<Song>, position:Int)-> Unit)
    : RecyclerView.Adapter<GridViewAdapter.GridViewHolder>() {
    private var songList: List<Song> = mutableListOf()

    inner class GridViewHolder(
        private val binding: SingleImageBinding,
        private val binding2: FragmentDashboardBinding?
    ) : RecyclerView.ViewHolder(binding.root) {
        fun applySong(song: Song) = with(binding) {
            imageView.load(song.albumPicture)
            tvTitle.text = song.artist

            root.setOnClickListener{
                playSong(song.mp3, songList, 0)
                binding2?.artistiPlaying?.text = song.artist
                binding2?.songPlaying?.text = song.name
                binding2?.albumPlayer?.load(song.albumPicture)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val binding = SingleImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GridViewHolder(binding, null)
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