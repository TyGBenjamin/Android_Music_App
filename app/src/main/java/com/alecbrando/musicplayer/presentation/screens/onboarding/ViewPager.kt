package com.alecbrando.musicplayer.presentation.screens.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alecbrando.musicplayer.adapters.ViewPagerAdapter
import com.alecbrando.musicplayer.databinding.FragmentViewPager2Binding
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [ViewPager.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class ViewPager : Fragment() {
    private var _binding: FragmentViewPager2Binding? = null
    private val binding: FragmentViewPager2Binding  get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentViewPager2Binding.inflate(inflater, container, false).also {
        _binding = it
    }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }
    private fun initViews() = with(binding) {
        val fragmentList = arrayListOf<Fragment>(
            FirstOnboard(),
            SecondOnboard(),
            ThirdOnboard()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        viewPager.adapter = adapter

    }


}