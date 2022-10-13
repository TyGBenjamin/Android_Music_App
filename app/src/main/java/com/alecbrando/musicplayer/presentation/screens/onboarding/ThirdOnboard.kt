package com.alecbrando.musicplayer.presentation.screens.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.alecbrando.musicplayer.databinding.FragmentThirdOnboardBinding
import com.alecbrando.musicplayer.domain.datastore.DataStorePrefSource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ThirdOnboard : Fragment() {
    private var _binding: FragmentThirdOnboardBinding? = null
    private val binding: FragmentThirdOnboardBinding get() = _binding!!

    @Inject
    lateinit var dataStorePrefImpl: DataStorePrefSource

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

    private fun navigateToDashboard() = lifecycleScope.launch {
        dataStorePrefImpl.setPreferenceInfo(true)
        val action = ViewPagerDirections.actionViewPager2ToDashboardFragment()
        findNavController().navigate(action)
    }


}