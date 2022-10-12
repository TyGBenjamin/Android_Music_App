package com.alecbrando.musicplayer.presentation.dashboard

import android.media.AudioManager
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.alecbrando.musicplayer.databinding.ThumbnailListBinding
import com.alecbrando.musicplayer.domain.models.Song

class ListAdapter (
    private val handleThumbnailClick: (song: Song) -> Unit
) : RecyclerView.Adapter<ListAdapter.ThumbnailViewHolder>() {
    private var musicList: MutableList<Song> = mutableListOf()

    class ThumbnailViewHolder(
        private val binding: ThumbnailListBinding,
        private val handleThumbnailClick: (song: Song) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun applyItem(song: Song) = with(binding) {
            tvSong.text = song.name
            tvAuthor.text = song.artist
            ivPoster.load(song.albumPicture)
            binding.root.setOnClickListener { handleThumbnailClick(song) }

//            val audioUrl = song.mp3
//            val mediaPlayer = MediaPlayer()
//            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
//            try {
//                mediaPlayer.setDataSource(audioUrl)
//                mediaPlayer.prepare()
//                mediaPlayer.start()
//                val duration = mediaPlayer.duration/1000
//                tvDuration.text = (duration/60).toString() +
//                        ":" + (duration%60).toString().padStart(2, '0')
//                mediaPlayer.stop()
//                mediaPlayer.reset()
//                mediaPlayer.release()
//            } catch (e: Exception) { }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThumbnailViewHolder {
        val binding =
            ThumbnailListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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