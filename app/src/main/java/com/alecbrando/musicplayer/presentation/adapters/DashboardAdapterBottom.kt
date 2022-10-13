package com.alecbrando.musicplayer.presentation.adapters

import android.content.ContentValues
import android.util.Log
import android.view.*
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.alecbrando.musicplayer.R
import com.alecbrando.musicplayer.databinding.DashboardRecyclerViewBinding
import com.alecbrando.musicplayer.databinding.DashboardRecyclerViewBottomBinding
import com.alecbrando.musicplayer.domains.models.Songs

class DashboardAdapterBottom(
    private val playSong:(mp3: String, songs: MutableList<Songs>) -> Unit
) : RecyclerView.Adapter<DashboardAdapterBottom.DashboardViewHolder>() {
    private var songs: MutableList<Songs> = mutableListOf()

    inner class DashboardViewHolder(
        private val binding: DashboardRecyclerViewBottomBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun displaySongs(song: Songs) = with(binding) {
            ivSongBottom.load(song.albumPicture)
            tvSongTitleBottom.text = song.name
            btnDownload.setOnClickListener {
                inflateDownloadButton(binding)
            }
            root.setOnClickListener{
                playSong(song.mp3, songs)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {

        val binding =
            DashboardRecyclerViewBottomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DashboardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val song = songs[position]
        holder.displaySongs(song)
    }

    override fun getItemCount(): Int = songs.size

    fun addSongs(songs: List<Songs>) {
        if (songs.isEmpty()) {
            this.songs = mutableListOf()
        } else {
            this.songs = songs as MutableList<Songs>
        }
        notifyDataSetChanged()
    }

    private fun inflateDownloadButton(binding: DashboardRecyclerViewBottomBinding) {
        val popup = PopupMenu(binding.root.context, binding.btnDownload)
        popup.inflate(R.menu.download_menu)
        popup.setOnMenuItemClickListener{ item ->
            when(item.itemId){
                R.id.btn_download -> true
                else -> false
            }
        }
        popup.show()
    }
}