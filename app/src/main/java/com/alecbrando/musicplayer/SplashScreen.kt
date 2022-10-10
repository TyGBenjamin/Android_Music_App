package com.alecbrando.musicplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val tv_name = findViewById<TextView>(R.id.tv_app_name)
        tv_name.alpha=0f
        tv_name.animate().setDuration(2500).alpha(1f).withEndAction{
            val intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in ,android.R.anim.fade_out)
            finish()
        }

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

    }

    }
