package com.alecbrando.musicplayer

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashScreenActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        initTimer()
        supportActionBar?.hide()
    }

    private fun initTimer() = lifecycleScope.launch {
        delay(DELAY_ANIMATION)
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)

    }

    companion object {
        const val DELAY_ANIMATION = 3000L
    }
}