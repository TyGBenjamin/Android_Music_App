package com.alecbrando.musicplayer.presentation.intro

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class IntroScreenAdapter (
    manager: FragmentManager
): FragmentPagerAdapter(manager) {
    private val fragmentList: MutableList<Fragment> = ArrayList()
    private val textList: MutableList<String> = ArrayList()

    override fun getCount() = fragmentList.size

    override fun getItem(position: Int) = fragmentList[position]

    override fun getPageTitle(position: Int): CharSequence? {
        return super.getPageTitle(position)
    }

    fun addFragment(fragment: Fragment, body: String) {
        fragmentList.add(fragment)
        textList.add(body)
    }
}