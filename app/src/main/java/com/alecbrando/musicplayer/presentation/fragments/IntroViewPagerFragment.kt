package com.alecbrando.musicplayer.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.alecbrando.musicplayer.R
import com.alecbrando.musicplayer.databinding.FragmentIntroViewPagerBinding
import com.alecbrando.musicplayer.presentation.adapters.IntroViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntroViewPagerFragment: Fragment() {
    private var _binding : FragmentIntroViewPagerBinding? = null
    private val binding : FragmentIntroViewPagerBinding get() = _binding!!
    private val introViewPagerAdapter by lazy { IntroViewPagerAdapter(::navigateToDashboard) }
    private val pagerImages = listOf(
        R.raw.view_pager_image1,
        R.raw.view_pager_image2,
        R.raw.view_pager_image3
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentIntroViewPagerBinding.inflate(inflater, container, false).also{
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        navigateToDashboard()
    }

    private fun initViews() = with(binding){
        viewPager.adapter = introViewPagerAdapter
        introViewPagerAdapter.addViewPagers(pagerImages)
    }

    private fun navigateToDashboard(){
        findNavController().navigate(IntroViewPagerFragmentDirections.actionIntroViewPagerFragmentToDashboardFragment())
    }
}


