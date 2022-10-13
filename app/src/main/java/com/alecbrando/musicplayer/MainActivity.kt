package com.alecbrando.musicplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import dagger.hilt.android.AndroidEntryPoint
import java.util.prefs.Preferences

/** Main Activity of module.
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var dataStore: DataStore<Preferences>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}