package com.example.feature.photos.di

import android.content.Context
import com.example.core.database.di.DatabaseProvider
import com.example.core.datastore.di.DataStoreProvider
import com.example.feature.photos.data.repository.PhotosRepositoryImpl
import com.example.feature.photos.domain.usecases.api.GetPhotosUseCase
import com.example.feature.photos.domain.usecases.api.DarkModeUseCase
import com.example.feature.photos.domain.usecases.impl.GetPhotosUseCaseImpl
import com.example.feature.photos.domain.usecases.impl.DarkModeUseCaseImpl
import com.example.network.di.NetWorkCallProvider

object PhotosFeatureProvider {

    fun provideGetPhotosUseCase(context: Context): GetPhotosUseCase {

        val repository = PhotosRepositoryImpl(
            photosRemoteDataSource = NetWorkCallProvider.photosRemoteDataSource,
            remoteKeysDao = DatabaseProvider.remoteKeysDao(context),
            photoDao = DatabaseProvider.photoDao(context),
            transactionRunner = DatabaseProvider.transactionRunnerDao(context),
            appSetting = DataStoreProvider.provideAppSetting(context)
        )

        return GetPhotosUseCaseImpl(repository)
    }

    fun provideUpdateDarkModeUseCase(context: Context): DarkModeUseCase {

        val repository = PhotosRepositoryImpl(
            photosRemoteDataSource = NetWorkCallProvider.photosRemoteDataSource,
            remoteKeysDao = DatabaseProvider.remoteKeysDao(context),
            photoDao = DatabaseProvider.photoDao(context),
            transactionRunner = DatabaseProvider.transactionRunnerDao(context),
            appSetting = DataStoreProvider.provideAppSetting(context)
        )

        return DarkModeUseCaseImpl(repository)
    }
}