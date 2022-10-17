package com.alecbrando.musicplayer.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.alecbrando.musicplayer.R
import com.alecbrando.musicplayer.databinding.FragmentThirdScreenBinding
import com.alecbrando.musicplayer.domain.datastore.DataStorePreferenceSource
import com.alecbrando.musicplayer.onboarding.ViewPagerDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ThirdScreen : Fragment() {
    private var _binding: FragmentThirdScreenBinding? = null
    private val binding: FragmentThirdScreenBinding get() = _binding!!

    @Inject
    lateinit var dataStore : DataStorePreferenceSource

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):  View = FragmentThirdScreenBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("third fragment")

        binding.btnContinue.setOnClickListener{
            navigateToDAshboard()
        }

    }

    private fun navigateToDAshboard() {
        lifecycleScope.launch{
            dataStore.setDataStore(true)
        }
        val action = ViewPagerDirections.actionViewPagerToDashboard()
        findNavController().navigate(action)

    }

}