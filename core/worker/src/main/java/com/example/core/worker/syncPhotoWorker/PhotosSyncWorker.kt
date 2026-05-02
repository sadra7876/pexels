package com.example.core.worker.syncPhotoWorker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.core.database.dao.PhotoDao
import com.example.core.mapper.toPhotoEntity
import com.example.network.networkCalls.photo.PhotosRemoteDataSource
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class PhotosSyncWorker @AssistedInject constructor(
    @Assisted context : Context,
    @Assisted workerParams: WorkerParameters,
    private val photosRemoteDataSource: PhotosRemoteDataSource,
    private val photoDao: PhotoDao,
) : CoroutineWorker( context, workerParams) {

    private suspend fun syncData() {

        Log.d("PhotosSyncWorker", "syncStarted")

        val photos = photosRemoteDataSource.getCuratedPhotos(1, 10).photos

        photoDao.insertAll(photos.map { it.toPhotoEntity(1) })

        Log.d("PhotosSyncWorker", "syncEnded")

    }


    override suspend fun doWork(): Result {
         return try {
            syncData()

            Result.success()
        } catch (e : Exception) {

             Log.e("worker", e.message ?: "")
             Result.failure()
        }
    }

}