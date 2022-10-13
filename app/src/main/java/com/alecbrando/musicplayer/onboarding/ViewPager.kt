package com.alecbrando.musicplayer.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.alecbrando.musicplayer.R
import com.alecbrando.musicplayer.databinding.FragmentViewPagerBinding
import com.alecbrando.musicplayer.domain.datastore.DataStorePreferenceSource
import com.alecbrando.musicplayer.onboarding.screens.FirstScreen
import com.alecbrando.musicplayer.onboarding.screens.SecondScreen
import com.alecbrando.musicplayer.onboarding.screens.ThirdScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class ViewPager : Fragment() {
    private var _binding: FragmentViewPagerBinding? = null
    private val binding: FragmentViewPagerBinding get() = _binding!!

    @Inject
    lateinit var dataStore : DataStorePreferenceSource

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
        lifecycleScope.launch{
            if(dataStore.getDataStore().first()){
                val action = ViewPagerDirections.actionViewPagerToDashboard()
                findNavController().navigate(action)
            }
            else{
                displayPagers()
            }
        }


    }
    private fun displayPagers()= with(binding){
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