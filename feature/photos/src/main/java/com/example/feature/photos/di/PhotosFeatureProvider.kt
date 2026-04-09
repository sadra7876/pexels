package com.example.feature.photos.di

import com.example.feature.photos.data.repository.PhotosRepositoryImpl
import com.example.feature.photos.domain.repository.PhotosRepository
import com.example.feature.photos.domain.usecases.GetPhotosUseCase
import com.example.feature.photos.domain.usecases.GetPhotosUseCaseImpl
import com.example.network.di.NetWorkCallProvider

object PhotosFeatureProvider {
    private val photosRepository: PhotosRepository =
        PhotosRepositoryImpl(NetWorkCallProvider.photosRemoteDataSource)

    val getPhotosUseCase : GetPhotosUseCase =
        GetPhotosUseCaseImpl(photosRepository)

}