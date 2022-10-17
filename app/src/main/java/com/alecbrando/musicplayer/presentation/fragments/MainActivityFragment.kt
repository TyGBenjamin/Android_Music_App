package com.alecbrando.musicplayer.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.alecbrando.musicplayer.databinding.FragmentMainActivityBinding
import com.alecbrando.musicplayer.domains.datastore.DataStoreRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule

@AndroidEntryPoint
class MainActivityFragment: Fragment() {
    private var _binding : FragmentMainActivityBinding? = null
    private val binding : FragmentMainActivityBinding get() = _binding!!

    @Inject
    lateinit var dataStore: DataStoreRepository

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
        lifecycleScope.launch {
            if (dataStore.getDataStore().first()) {
                findNavController().navigate(MainActivityFragmentDirections.actionMainActivityFragmentToDashboardFragment())
            } else {
                findNavController().navigate(MainActivityFragmentDirections.actionMainActivityFragmentToIntroViewPagerFragment())
            }
        }
    }
}