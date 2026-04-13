package com.example.feature.photos.di

import android.content.Context
import com.example.core.database.di.DatabaseProvider
import com.example.feature.photos.data.repository.PhotosRepositoryImpl
import com.example.feature.photos.domain.usecases.GetPhotosUseCase
import com.example.feature.photos.domain.usecases.GetPhotosUseCaseImpl
import com.example.network.di.NetWorkCallProvider

object PhotosFeatureProvider {

    fun provide(context: Context): GetPhotosUseCase {

        val repository = PhotosRepositoryImpl(
            photosRemoteDataSource = NetWorkCallProvider.photosRemoteDataSource,
            remoteKeysDao = DatabaseProvider.remoteKeysDao(context),
            photoDao = DatabaseProvider.photoDao(context),
            transactionRunner = DatabaseProvider.transactionRunnerDao(context)
        )

        return GetPhotosUseCaseImpl(repository)
    }
}