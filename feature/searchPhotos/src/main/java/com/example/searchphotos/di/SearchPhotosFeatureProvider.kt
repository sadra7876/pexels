package com.example.searchphotos.di

import android.content.Context
import com.example.core.database.di.DatabaseProvider
import com.example.network.di.NetWorkCallProvider
import com.example.searchphotos.data.repository.SearchPhotoRepositoryImpl
import com.example.searchphotos.domain.repository.SearchPhotoRepository
import com.example.searchphotos.domain.usecases.api.SearchHistoryUseCase
import com.example.searchphotos.domain.usecases.api.SearchPhotosUseCase
import com.example.searchphotos.domain.usecases.impl.SearchHistoryUseCaseImpl
import com.example.searchphotos.domain.usecases.impl.SearchPhotosUseCaseImpl

object SearchPhotosFeatureProvider {
    fun provideSearchPhotosUseCase(context: Context): SearchPhotosUseCase {

        val searchPhotoRepository : SearchPhotoRepository = SearchPhotoRepositoryImpl(
            photosRemoteDataSource = NetWorkCallProvider.photosRemoteDataSource,
            searchHistoryDao = DatabaseProvider.searcHistoryDao(context),
            photoDao = DatabaseProvider.photoDao(context)
        )

        return SearchPhotosUseCaseImpl(searchPhotoRepository)
    }

    fun provideSearchHistoryUseCase(context: Context): SearchHistoryUseCase {

        val searchPhotoRepository : SearchPhotoRepository = SearchPhotoRepositoryImpl(
            photosRemoteDataSource = NetWorkCallProvider.photosRemoteDataSource,
            searchHistoryDao = DatabaseProvider.searcHistoryDao(context),
            photoDao = DatabaseProvider.photoDao(context)

        )

        return SearchHistoryUseCaseImpl(searchPhotoRepository)
    }
}