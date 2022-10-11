package com.alecbrando.musicplayer.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alecbrando.musicplayer.databinding.SingleImageBinding

class GridViewAdapter : RecyclerView.Adapter<GridViewAdapter.GridViewHolder>() {

    inner class GridViewHolder(
        private val binding: SingleImageBinding
    ) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}