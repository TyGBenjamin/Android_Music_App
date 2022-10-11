package com.alecbrando.musicplayer.presentation.screens.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.alecbrando.musicplayer.R
import com.alecbrando.musicplayer.databinding.FragmentDashboardBinding
import com.alecbrando.musicplayer.databinding.FragmentThirdOnboardBinding


class ThirdOnboard : Fragment() {
    private var _binding: FragmentThirdOnboardBinding? = null
    private val binding: FragmentThirdOnboardBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentThirdOnboardBinding.inflate(inflater,container, false).also{
        _binding = it
    }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }
    private fun initListeners() = with(binding) {
        btnContinue.setOnClickListener {
            println("Continue Button clicked")
            navigateToDashboard()
        }
    }

    private fun navigateToDashboard() {
        val action = ViewPagerDirections.actionViewPager2ToDashboardFragment()
        findNavController().navigate(action)
    }


}