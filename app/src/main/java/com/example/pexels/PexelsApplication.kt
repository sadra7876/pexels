package com.example.pexels

import android.app.Application
import androidx.work.Configuration
import com.example.core.worker.syncPhotoWorker.PhotosSyncWorkerFactory
import scheduleSyncWork

class PexelsApplication : Application(), Configuration.Provider {

    override val workManagerConfiguration : Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(PhotosSyncWorkerFactory())
            .build()

    override fun onCreate() {
        super.onCreate()
        scheduleSyncWork(this)
    }
}