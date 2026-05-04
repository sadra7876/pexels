package com.example.core.worker.di

import android.content.Context
import androidx.work.WorkerParameters
import com.example.core.worker.syncPhotoWorker.PhotosSyncWorker
import org.koin.dsl.module

val workerModule = module {

    factory { (context: Context, workerParams: WorkerParameters) ->
        PhotosSyncWorker(
            context,
            workerParams,
            get(),
            get()
        )
    }
}