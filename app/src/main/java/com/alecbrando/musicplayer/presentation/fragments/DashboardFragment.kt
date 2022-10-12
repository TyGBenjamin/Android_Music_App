package com.alecbrando.musicplayer.presentation.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.alecbrando.musicplayer.R
import com.alecbrando.musicplayer.databinding.FragmentDashboardBinding
import com.alecbrando.musicplayer.presentation.adapters.DashboardAdapter
import com.alecbrando.musicplayer.presentation.adapters.DashboardAdapterBottom
import com.alecbrando.musicplayer.presentation.viewmodels.DashboardViewModel
import com.alecbrando.musicplayer.presentation.viewmodels.DashboardViewModelBottom
import com.alecbrando.musicplayer.resources.Resource
import com.alecbrando.musicplayer.utils.collectLatestLifecycleFlow
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding: FragmentDashboardBinding get() = _binding!!
    private val viewModel by viewModels<DashboardViewModel>()
    private val viewModelBottom by viewModels<DashboardViewModelBottom>()
    private val dashboardAdapter by lazy { DashboardAdapter() }
    private val dashboardAdapterBottom by lazy { DashboardAdapterBottom() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentDashboardBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners(view)
    }

    private fun initViews() = with(binding) {
        collectLatestLifecycleFlow(viewModel.songList){ songList ->
            when (songList) {
                is Resource.Idle -> {}
                is Resource.Error -> {}
                is Resource.Loading -> {}
                is Resource.Success ->
                    rvDashboardRecyclerView.adapter = dashboardAdapter.apply {
                        addSongs(songList.data.songs)
                    }
            }
        }
        collectLatestLifecycleFlow(viewModelBottom.songList){ songList ->
            when (songList) {
                is Resource.Idle -> {}
                is Resource.Error -> {}
                is Resource.Loading -> {}
                is Resource.Success ->
                    rvDashboardRecyclerViewBottom.adapter = dashboardAdapterBottom.apply {
                        addSongs(songList.data.songs)
                    }
            }
        }
    }

    private fun initListeners(view: View) = with(binding) {
        btnSearch.setOnClickListener {
            searchMenu(view)
        }
    }

    private fun searchMenu(view: View) = with(binding) {
        val popup = PopupMenu(context, view)
        popup.inflate(R.menu.search_menu)
        popup.gravity = Gravity.END
        popup.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mi_country -> {
                Log.d(TAG, "onOptionsItemSelected: Wtf")
            }
            R.id.mi_hip_hop -> {}
            R.id.mi_edm -> {}
            R.id.mi_random -> {}
            R.id.mi_jazz -> {}
            R.id.mi_metal -> {}
            R.id.mi_reggae -> {}
            R.id.mi_synthwave -> {}
        }
        return true
    }
}