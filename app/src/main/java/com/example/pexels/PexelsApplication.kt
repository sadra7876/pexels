package com.example.pexels

import android.app.Application
import androidx.work.Configuration
import com.example.common.loader.di.imageLoaderModule
import com.example.core.database.di.databaseModule
import com.example.core.datastore.di.dataStoreModule
import com.example.core.worker.di.workerModule
import com.example.feature.favoritephotos.di.favoritePhotosFeatureModule
import com.example.feature.photodetail.di.photoDetailFeatureModule
import com.example.feature.photos.di.photosFeatureModule
import com.example.network.di.endPointModule
import com.example.network.di.networkCallModule
import com.example.network.di.networkModule
import com.example.searchphotos.di.searchPhotosFeatureModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.factory.KoinWorkerFactory
import org.koin.core.context.startKoin


class PexelsApplication : Application(), Configuration.Provider {


    override val workManagerConfiguration : Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(KoinWorkerFactory())
            .build()

    override fun onCreate() {
        super.onCreate()
//        scheduleSyncWork(this)

        startKoin {
            androidContext(this@PexelsApplication)
            modules(
                listOf(
                    imageLoaderModule,
                    databaseModule,
                    dataStoreModule,
                    endPointModule,
                    networkCallModule,
                    networkModule,
                    workerModule,
                    favoritePhotosFeatureModule,
                    photoDetailFeatureModule,
                    photosFeatureModule,
                    searchPhotosFeatureModule
                )
            )
        }
    }
}