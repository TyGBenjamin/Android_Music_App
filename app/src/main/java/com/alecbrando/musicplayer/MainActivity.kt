package com.alecbrando.musicplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.alecbrando.musicplayer.util.Constants
import com.alecbrando.musicplayer.workmanager.FileDownloadWorker
import dagger.hilt.android.AndroidEntryPoint
import java.time.Duration
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