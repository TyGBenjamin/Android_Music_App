package com.alecbrando.musicplayer.presentation.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.alecbrando.musicplayer.adapters.ListViewAdapter
import com.alecbrando.musicplayer.databinding.FragmentDashboardBinding
import com.alecbrando.musicplayer.util.Constants.TAG
import com.alecbrando.musicplayer.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding: FragmentDashboardBinding get() = _binding!!
    private val viewModel by viewModels<ListViewModel>()
    private val listViewAdapter by lazy { ListViewAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = FragmentDashboardBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setUpMenu()
//
        initViews()
//        initListeners()

    }

    private fun initViews() = with(binding) {
        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
            listOf(
                launch {
                    viewModel.songList.collectLatest { songState ->
                        when (songState) {
                            is Resource.Error -> Log.d(TAG, "error: ${songState.message}")
                            is Resource.Loading -> Log.d("LOADING", "loading...")
                            is Resource.Success -> {
                                Log.d(TAG, "success: ${songState.data.songs}")
                                rvView2.adapter = listViewAdapter.apply {
                                    addItems(songState.data.songs)
                                }
                            }
                        }
                    }
                },
                launch {
                    // TODO: build out second adapter
                }
            ).joinAll()

//                }
        }

    }
}