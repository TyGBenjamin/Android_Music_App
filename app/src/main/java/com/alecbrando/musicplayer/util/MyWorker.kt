package com.alecbrando.musicplayer.util

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

//class MyWorker(context: Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {
//    //    override fun doWork(): Result {
////        TODO("Not yet implemented")
////
////    }
//    override fun doWork(): Result {
////        val resourceUri = inputData.getString(KEY_IMAGE_URI)
//        return try {
//            if (resourceUri.isNullOrEmpty()) {
//                Timber.e("Invalid input uri")
//                throw IllegalArgumentException("Invalid input uri")
//            }
//
////            val outputData = blurAndWriteImageToFile(resourceUri)
//            Result.success(outputData)
//        } catch (throwable: Throwable) {
////            Timber.e(throwable, "Error applying blur")
//            Result.failure()
//        }
//    }
//}