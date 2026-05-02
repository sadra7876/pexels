package com.example.feature.photodetail.di

import com.example.feature.photodetail.data.repository.PhotoDetailRepositoryImpl
import com.example.feature.photodetail.domain.repository.PhotoDetailRepository
import com.example.feature.photodetail.domain.usecases.api.FavoritePhotoUseCase
import com.example.feature.photodetail.domain.usecases.api.GetPhotoUseCase
import com.example.feature.photodetail.domain.usecases.impl.FavoritePhotoUseCaseImpl
import com.example.feature.photodetail.domain.usecases.impl.GetPhotoUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PhotoDetailFeatureModule {

    @Binds
    internal abstract fun bindFavoritePhotoUseCase (useCase: FavoritePhotoUseCaseImpl): FavoritePhotoUseCase

    @Binds
    internal abstract fun bindGetPhotoUseCase (useCase: GetPhotoUseCaseImpl): GetPhotoUseCase

    @Binds
    internal abstract fun bindPhotosRepository(repository: PhotoDetailRepositoryImpl): PhotoDetailRepository
}