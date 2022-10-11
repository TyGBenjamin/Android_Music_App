package com.alecbrando.musicplayer.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alecbrando.musicplayer.R
import com.alecbrando.musicplayer.databinding.FragmentViewPagerBinding
import com.alecbrando.musicplayer.onboarding.screens.FirstScreen
import com.alecbrando.musicplayer.onboarding.screens.SecondScreen
import com.alecbrando.musicplayer.onboarding.screens.ThirdScreen





class ViewPager : Fragment() {
    private var _binding: FragmentViewPagerBinding? = null
    private val binding: FragmentViewPagerBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentViewPagerBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() = with(binding) {
        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        viewPager.adapter = adapter

    }


}