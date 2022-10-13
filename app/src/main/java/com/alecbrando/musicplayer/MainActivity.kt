package com.alecbrando.musicplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.alecbrando.musicplayer.domain.datastore.DataStorePrefSource
import com.alecbrando.musicplayer.presentation.screens.onboarding.ViewPagerDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var dataStorePrefImpl: DataStorePrefSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        runBlocking {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            val navController = navHostFragment.navController
            if (dataStorePrefImpl.getPreferenceInfo().first()) {
                val action = ViewPagerDirections.actionViewPager2ToDashboardFragment()
                navController.navigate(action)
            }
        }
//        supportActionBar?.hide()
    }

}