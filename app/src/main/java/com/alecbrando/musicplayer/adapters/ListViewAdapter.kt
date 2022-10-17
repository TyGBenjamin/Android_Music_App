package com.alecbrando.musicplayer.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import androidx.work.*
import coil.load
import com.alecbrando.musicplayer.data.model.Song
import com.alecbrando.musicplayer.databinding.SingleSongBinding
import com.alecbrando.musicplayer.workManager.DownloadWorker


class ListViewAdapter(
    private val playSong :(song: Song, songs: List<Song>, position: Int)-> Unit
) : RecyclerView.Adapter<ListViewAdapter.ListViewHolder>() {
    private var songList: List<Song> = mutableListOf()

    inner class ListViewHolder(
        private val binding: SingleSongBinding,

    ) : RecyclerView.ViewHolder(binding.root) {

        @RequiresApi(Build.VERSION_CODES.M)
        fun applySong(song: Song) = with(binding) {
            imageView.load(song.albumPicture)
            tvTitle.text = song.name
            tvArtist.text = song.artist
            println("apply")
//            val downloadRequest = OneTimeWorkRequestBuilder<DownloadWorker>()
//                .setConstraints(
//                    Constraints.Builder()
//                        .setRequiredNetworkType(
//                            NetworkType.CONNECTED
//                        )
//                        .build()
//                )
//                .build()
//            playSong(song.mp3, songList)

            root.setOnClickListener{
                println("CLICK song has been clicked ${song.mp3}")
                playSong(song, songList,0)


            }
            btnDownload.setOnClickListener{
                println("download button for ${song}clicked")
//                inflateDownloadMenu(song.mp3, song.name, binding, binding.btnDownload.id)
                val url = song.mp3.subSequence(47, song.mp3.lastIndex+1) as String
                DownloadWorker.url = url
                oneTimeWorkRequest(binding)

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = SingleSongBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = songList[position]
        holder.applySong(item)
    }

    override fun getItemCount() = songList.size


    fun addItems(songList: List<Song>) {
        this.songList = songList
        notifyDataSetChanged()
    }
}


@RequiresApi(Build.VERSION_CODES.M)
//private fun inflateDownloadMenu(songMp3: String, songName: String, binding: SingleSongBinding, id: Int){
//    val popup = PopupMenu(binding.btnDownload.context, binding.btnDownload)
//    popup.inflate(R.menu.download)
//    popup.setOnMenuItemClickListener{ item ->        when(item.itemId){
//        R.menu.download -> {
//            var url = songMp3.subSequence(47, songMp3.lastIndex+1) as String
//            DownloadWorker.url = url
//            DownloadWorker.songName = songName
//            println("I AM DOWNLOADING THIS  $songName    $url")
//
//            oneTimeWorkRequest(binding)
//            true
//        }
//        else -> {
//            false
//        }
//    }
//    }
//    popup.show()
//}

fun oneTimeWorkRequest(binding: SingleSongBinding){
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
        .build()

    val workRequest : WorkRequest = OneTimeWorkRequest.Builder(DownloadWorker::class.java)
        .setConstraints(constraints)
        .build()

    WorkManager.getInstance(binding.btnDownload.context).enqueue(workRequest)
}

