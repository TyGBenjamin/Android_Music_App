package com.alecbrando.musicplayer.presentation.adapters

import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import androidx.work.*
import coil.load
import com.alecbrando.musicplayer.MainActivity
import com.alecbrando.musicplayer.R
import com.alecbrando.musicplayer.workmanager.FileDownloadWorker
import com.alecbrando.musicplayer.databinding.DashboardRecyclerViewBottomBinding
import com.alecbrando.musicplayer.domains.models.Songs

class DashboardAdapterBottom(
    private val playOrPauseSong:(mp3: String, songs: MutableList<Songs>) -> Unit,
) : RecyclerView.Adapter<DashboardAdapterBottom.DashboardViewHolder>() {
    private var songs: MutableList<Songs> = mutableListOf()
    private val mainActivity by lazy { MainActivity() }

    inner class DashboardViewHolder(
        private val binding: DashboardRecyclerViewBottomBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.M)
        fun displaySongs(song: Songs) = with(binding) {
            ivSongBottom.load(song.albumPicture)
            tvSongTitleBottom.text = song.name
            btnDownload.setOnClickListener {
                inflateDownloadMenu(song.mp3, song.name, binding, binding.btnDownload.id)
            }
            root.setOnClickListener{
                Log.d(TAG, "displaySongs: this is song mp3 ${song.mp3} this is id,${song.id} name ${song.name}")
                playOrPauseSong(song.mp3, songs)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val binding = DashboardRecyclerViewBottomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DashboardViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.M)
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

    @RequiresApi(Build.VERSION_CODES.M)
    private fun inflateDownloadMenu(songMp3: String, songName: String, binding: DashboardRecyclerViewBottomBinding, id: Int){
        val popup = PopupMenu(binding.btnDownload.context, binding.btnDownload)
        popup.inflate(R.menu.download_menu)
        popup.setOnMenuItemClickListener{ item ->
            when(item.itemId){
                R.id.mi_download -> {
                    var url = songMp3.subSequence(47, songMp3.lastIndex+1) as String
                    FileDownloadWorker.songName = songName
                    FileDownloadWorker.url = url
                    oneTimeWorkSchedule(binding)
                    true
                }
                else -> {
                    false
                }
            }
        }
        popup.show()
    }

    fun oneTimeWorkSchedule(binding: DashboardRecyclerViewBottomBinding){
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .build()

        val workRequest : WorkRequest = OneTimeWorkRequest.Builder(FileDownloadWorker::class.java)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(binding.btnDownload.context).enqueue(workRequest)
    }
}