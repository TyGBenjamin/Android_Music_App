package com.alecbrando.musicplayer.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.alecbrando.musicplayer.databinding.DashboardRecyclerViewBinding
import com.alecbrando.musicplayer.domains.models.Songs

class DashboardAdapter(
    private val setSongInfo:(mp3: String, songs: MutableList<Songs>) -> Unit
) : RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder>() {
    private var songs: MutableList<Songs> = mutableListOf()

    inner class DashboardViewHolder(
        private val binding: DashboardRecyclerViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun displaySongs(song: Songs) = with(binding) {
            ivSong.load(song.albumPicture)
            tvSongTitle.text = song.name
            root.setOnClickListener{
                setSongInfo(song.mp3, songs)
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
}
