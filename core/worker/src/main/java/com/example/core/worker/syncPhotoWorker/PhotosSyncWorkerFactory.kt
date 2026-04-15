package com.example.core.worker.syncPhotoWorker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.core.database.di.DatabaseProvider
import com.example.network.di.NetWorkCallProvider

class PhotosSyncWorkerFactory() : WorkerFactory(){

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return when (workerClassName) {
            PhotosSyncWorker::class.java.name -> {
                PhotosSyncWorker(
                    context = appContext,
                    workerParams = workerParameters,
                    photosRemoteDataSource = NetWorkCallProvider.photosRemoteDataSource,
                    photoDao = DatabaseProvider.photoDao(appContext),
                )
            }
            else -> null
        }
    }
}