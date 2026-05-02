package com.example.feature.favoritephotos.di

import com.example.feature.favoritephotos.data.repository.FavoritePhotosRepositoryImpl
import com.example.feature.favoritephotos.domain.repository.FavoritePhotosRepository
import com.example.feature.favoritephotos.domain.usecases.GetFavoritePhotosUseCase
import com.example.feature.favoritephotos.domain.usecases.GetFavoritePhotosUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class FavoritePhotosFeatureModule {

    @Binds
    internal abstract fun bindGetFavoritePhotosUseCase(useCase: GetFavoritePhotosUseCaseImpl): GetFavoritePhotosUseCase

    @Binds
    internal abstract fun bindFavoritePhotosRepository(repository: FavoritePhotosRepositoryImpl): FavoritePhotosRepository
}