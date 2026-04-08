package com.example.feature.photos.di

import com.example.network.di.NetWorkCallProvider

object FirstPageProvider {
    private val firstPageRepository: com.example.feature.photos.domain.repository.FirstPageRepository =
        com.example.feature.photos.data.repository.FirstPageRepositoryImpl(NetWorkCallProvider.firstPageCall)

    private val getPhotosUseCase : com.example.feature.photos.domain.usecases.GetPhotosUseCase =
        com.example.feature.photos.domain.usecases.GetPhotosUseCaseImpl(firstPageRepository)

}