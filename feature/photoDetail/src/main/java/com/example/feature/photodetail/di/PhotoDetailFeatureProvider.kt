package com.example.feature.photodetail.di

import android.content.Context
import com.example.core.database.di.DatabaseProvider
import com.example.feature.photodetail.data.repository.PhotoDetailRepositoryImpl
import com.example.feature.photodetail.domain.usecases.api.FavoritePhotoUseCase
import com.example.feature.photodetail.domain.usecases.api.GetPhotoUseCase
import com.example.feature.photodetail.domain.usecases.impl.FavoritePhotoUseCaseImpl
import com.example.feature.photodetail.domain.usecases.impl.GetPhotoUseCaseImpl
import com.example.network.di.NetWorkCallProvider

object PhotoDetailFeatureProvider {

    fun provideGetPhotoUseCase(context: Context): GetPhotoUseCase{
        val repository = PhotoDetailRepositoryImpl(
            photosRemoteDataSource = NetWorkCallProvider.photosRemoteDataSource,
            favoriteDao = DatabaseProvider.favoriteDao(context),
            photoDao = DatabaseProvider.photoDao(context)
        )
        return GetPhotoUseCaseImpl(repository)
    }

    fun provideFavoritePhotoUseCase(context: Context): FavoritePhotoUseCase {
        val repository = PhotoDetailRepositoryImpl(
            photosRemoteDataSource = NetWorkCallProvider.photosRemoteDataSource,
            favoriteDao = DatabaseProvider.favoriteDao(context),
            photoDao = DatabaseProvider.photoDao(context)
        )
        return FavoritePhotoUseCaseImpl(repository)
    }

}