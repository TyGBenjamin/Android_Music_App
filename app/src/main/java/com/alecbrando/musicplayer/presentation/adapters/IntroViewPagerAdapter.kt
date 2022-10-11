package com.alecbrando.musicplayer.presentation.adapters

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.alecbrando.musicplayer.databinding.FragmentIntroViewPagerBinding
import com.alecbrando.musicplayer.databinding.ViewPagerItemsBinding

class IntroViewPagerAdapter(
    val navigateToDashboard: () -> Unit
): RecyclerView.Adapter<IntroViewPagerAdapter.IntroViewPagerHolder>() {
    private var viewPagers : List<Int> = emptyList()
    val pagerText = listOf(
        "This is the first pager text",
        "This is the second pager text",
        "This is the third pager text"
    )

    inner class IntroViewPagerHolder(
        private val binding: ViewPagerItemsBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun displayIntroViewPager(pager: Int) = with(binding){
            ivPagerImage.setAnimation(pager)
            tvPagerText.text = pagerText[absoluteAdapterPosition]
            if(absoluteAdapterPosition == viewPagers.size-1){
                btnPager.visibility = View.VISIBLE.also {
                    btnPager.setOnClickListener {
                        navigateToDashboard()
                    }
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroViewPagerHolder {
        val binding = ViewPagerItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IntroViewPagerHolder(binding)
    }

    override fun onBindViewHolder(holder: IntroViewPagerHolder, position: Int) {
        holder.displayIntroViewPager(viewPagers[position])
    }

    override fun getItemCount(): Int = viewPagers.size

    fun addViewPagers(pagers: List<Int>){
        viewPagers = pagers
        notifyDataSetChanged()
    }

}
