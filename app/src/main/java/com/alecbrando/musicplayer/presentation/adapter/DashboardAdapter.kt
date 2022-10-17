package com.alecbrando.musicplayer.presentation.adapter

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import androidx.work.*
import coil.load
import com.alecbrando.musicplayer.R
import com.alecbrando.musicplayer.databinding.FragmentDashboardBinding
import com.alecbrando.musicplayer.databinding.SongBinding
import com.alecbrando.musicplayer.domain.models.Song
import com.alecbrando.musicplayer.domain.models.SongList
import com.alecbrando.musicplayer.domain.models.SongX
import com.alecbrando.musicplayer.workmanager.FileDownloadWorker


class DashboardAdapter(
    private val playSong:(songDeets:SongX,mp3: String, songs: List<SongX>) -> Unit,
):RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder>() {
    private lateinit var songs: List<SongX>

    inner class DashboardViewHolder(
        private val binding: SongBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        @RequiresApi(Build.VERSION_CODES.M)
        fun apply(song: SongX) = with(binding) {
//            Log.d("Logger", "displaySongs: ${song.name}")
            imageView.load(song.albumPicture)
            tvTitle.text = song.name
            tvArtist.text = song.artist
            btnDownload.setOnClickListener{
                inflateDownloadMenu(song.mp3, song.name, binding, binding.btnDownload.id)
            }



            root.setOnClickListener{
//                println("Song Clicked ${song.name}")

                playSong(song,song.mp3, songs)

            }






        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
    DashboardViewHolder{
        val binding = SongBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DashboardViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.M)
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

    @RequiresApi(Build.VERSION_CODES.M)
    private fun inflateDownloadMenu(songMp3: String, songName: String, binding: SongBinding, id: Int){
        val popup = PopupMenu(binding.btnDownload.context, binding.btnDownload)
        popup.inflate(R.menu.download_menu)
        popup.setOnMenuItemClickListener{ item ->
            when(item.itemId){
                R.id.menu_download -> {
                    var url = songMp3.subSequence(47, songMp3.lastIndex+1) as String
                    FileDownloadWorker.url = url
                    FileDownloadWorker.songName = songName
                    println("I AM DOWNLOADING THIS  $songName    $url")

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
    fun oneTimeWorkSchedule(binding: SongBinding){
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .build()

        val workRequest : WorkRequest = OneTimeWorkRequest.Builder(FileDownloadWorker::class.java)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(binding.btnDownload.context).enqueue(workRequest)
        println("IM Working")
    }





}