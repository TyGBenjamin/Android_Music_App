package com.alecbrando.musicplayer.presentation.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.alecbrando.musicplayer.databinding.FragmentMainActivityBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.concurrent.schedule
import kotlin.math.log

@AndroidEntryPoint
class MainActivityFragment: Fragment() {
    private var _binding : FragmentMainActivityBinding? = null
    private val binding : FragmentMainActivityBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMainActivityBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        Timer("SettingUp", false).schedule(1) {
            lifecycleScope.launch{
                navigateToIntroViewPagerFragment()
            }
        }
    }

    private fun navigateToIntroViewPagerFragment() {
        findNavController().navigate(MainActivityFragmentDirections.actionMainActivityFragmentToIntroViewPagerFragment())
    }

}