package com.alecbrando.musicplayer.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter (
    list: ArrayList<Fragment>,
    fragmentManger: FragmentManager,
    lifeCycle: Lifecycle) :
    FragmentStateAdapter(fragmentManger, lifeCycle) {

    private val fragmentList: ArrayList<Fragment> = list
    override fun getItemCount() = fragmentList.size

    override fun createFragment(position: Int) = fragmentList[position]
}