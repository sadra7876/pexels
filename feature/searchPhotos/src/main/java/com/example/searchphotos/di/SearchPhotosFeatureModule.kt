package com.example.searchphotos.di

import com.example.searchphotos.data.repository.SearchPhotoRepositoryImpl
import com.example.searchphotos.domain.repository.SearchPhotoRepository
import com.example.searchphotos.domain.usecases.api.SearchHistoryUseCase
import com.example.searchphotos.domain.usecases.api.SearchPhotosUseCase
import com.example.searchphotos.domain.usecases.impl.SearchHistoryUseCaseImpl
import com.example.searchphotos.domain.usecases.impl.SearchPhotosUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class SearchPhotosFeatureModule {

    @Binds
    internal abstract fun bindSearchPhotosUseCase(useCase: SearchPhotosUseCaseImpl): SearchPhotosUseCase

    @Binds
    internal abstract fun bindSearchHistoryUseCase (useCase: SearchHistoryUseCaseImpl): SearchHistoryUseCase

    @Binds
    internal abstract fun bindRepository(repository: SearchPhotoRepositoryImpl): SearchPhotoRepository
}