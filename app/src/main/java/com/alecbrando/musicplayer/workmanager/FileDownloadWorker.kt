package com.alecbrando.musicplayer.workmanager

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.alecbrando.musicplayer.R
import com.alecbrando.musicplayer.data.remote.FileApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import kotlin.random.Random

class FileDownloadWorker(
    private val context: Context,
    workerParameters: WorkerParameters
): CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        startForegroundService()
        delay(5000L)
        val response = FileApi.instance.downloadSong()
        response.body()?.let{body ->
            return withContext(Dispatchers.IO){
                val file = File(context.cacheDir, "song.mp3")
                val outputStream = FileOutputStream(file)
                outputStream.use { stream ->
                    try {
                        stream.write(body.bytes())
                    }catch (e: IOException){
                        return@withContext Result.failure(
                            workDataOf(
                                    WorkerParams.ERROR_MSG to e.localizedMessage
                            )
                        )
                    }
                }
                Result.success(
                    workDataOf(
                        WorkerParams.IMAGE_URI to file.toUri().toString()
                    )
                )
            }


        }
        if (!response.isSuccessful){
            if(response.code().toString().startsWith("5")){
                return  Result.retry()
            }
            return  Result.failure(
                workDataOf(
                    WorkerParams.ERROR_MSG to "Network Error"
                )
            )
        }
        return Result.failure(
            workDataOf(WorkerParams.ERROR_MSG to "Unknown Error")
        )
    }

    private  suspend fun  startForegroundService(){
        setForeground(
            ForegroundInfo(
                Random.nextInt(),
                NotificationCompat.Builder(context, "download_chanel")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentText("Download in progress")
                    .build()


            )
        )
    }
}
