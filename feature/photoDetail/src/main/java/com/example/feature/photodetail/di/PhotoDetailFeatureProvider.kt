package com.example.feature.photodetail.di

import com.example.feature.photodetail.data.repository.PhotoDetailRepositoryImpl
import com.example.feature.photodetail.domain.repository.PhotoDetailRepository
import com.example.feature.photodetail.domain.usecases.GetPhotoUseCase
import com.example.feature.photodetail.domain.usecases.GetPhotoUseCaseImpl
import com.example.network.di.NetWorkCallProvider

object PhotoDetailFeatureProvider {
    private val photoDetailRepository: PhotoDetailRepository =
        PhotoDetailRepositoryImpl(NetWorkCallProvider.photosRemoteDataSource)

    val getPhotoUseCase : GetPhotoUseCase =
        GetPhotoUseCaseImpl(photoDetailRepository)
}