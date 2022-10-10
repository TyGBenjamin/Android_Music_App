package com.alecbrando.musicplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.concurrent.schedule

/** Display Splash-screen for 2 seconds.
 * @author Ngu Nguyen 10/10/2022
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val splash1 = R.id.iv_splash_screen1

        Timer("SettingUp", false).schedule(2000) {
            setUpSplashScreen()
        }
    }

    private fun setUpSplashScreen() {}
}