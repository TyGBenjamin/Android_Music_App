package com.alecbrando.musicplayer.presentation.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import com.alecbrando.musicplayer.R
import com.alecbrando.musicplayer.databinding.ActivityIntroScreenBinding
import com.alecbrando.musicplayer.presentation.dashboard.DashboardActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntroScreenActivity : AppCompatActivity() {
    private val binding by lazy { ActivityIntroScreenBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    private fun init() = with(binding) {
        val adapter = IntroScreenAdapter(supportFragmentManager)
        adapter.addFragment(IntroFirstFragment(), "One")
        adapter.addFragment(IntroSecondFragment(), "Two")
        adapter.addFragment(IntroThirdFragment(@IntroScreenActivity::moveToDashboard), "Three")
        viewPager.adapter = adapter
    }

    private fun moveToDashboard() {
        startActivity(Intent(this, DashboardActivity::class.java))
        finish()
    }
}