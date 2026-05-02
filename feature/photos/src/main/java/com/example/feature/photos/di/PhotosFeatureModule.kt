package com.example.feature.photos.di

import com.example.feature.photos.data.repository.PhotosRepositoryImpl
import com.example.feature.photos.domain.repository.PhotosRepository
import com.example.feature.photos.domain.usecases.api.DarkModeUseCase
import com.example.feature.photos.domain.usecases.api.GetPhotosUseCase
import com.example.feature.photos.domain.usecases.impl.DarkModeUseCaseImpl
import com.example.feature.photos.domain.usecases.impl.GetPhotosUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PhotosFeatureModule {

    @Binds
    internal abstract fun bindDarkModeUseCase (useCase: DarkModeUseCaseImpl): DarkModeUseCase

    @Binds
    internal abstract fun bindGetPhotosUseCase(useCase: GetPhotosUseCaseImpl): GetPhotosUseCase

    @Binds
    internal abstract fun bindPhotosRepository(repository: PhotosRepositoryImpl): PhotosRepository

}
