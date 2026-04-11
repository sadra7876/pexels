package com.example.searchphotos.di

import com.example.network.di.NetWorkCallProvider
import com.example.searchphotos.data.repository.SearchPhotoRepositoryImpl
import com.example.searchphotos.domain.repository.SearchPhotoRepository
import com.example.searchphotos.domain.usecases.SearchPhotosUseCase
import com.example.searchphotos.domain.usecases.SearchPhotosUseCaseImpl

object SearchPhotosFeatureProvider {
    private val searchPhotoRepository : SearchPhotoRepository = SearchPhotoRepositoryImpl(
        NetWorkCallProvider.photosRemoteDataSource
    )

    val searchPhotosUseCase : SearchPhotosUseCase = SearchPhotosUseCaseImpl(
        searchPhotoRepository
    )
}