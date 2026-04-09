package com.example.feature.photodetail.data.repository

import com.example.feature.photodetail.data.mapper.toPhotoDN
import com.example.feature.photodetail.domain.models.PhotoDN
import com.example.feature.photodetail.domain.repository.PhotoDetailRepository
import com.example.network.networkCalls.photo.PhotosRemoteDataSource

internal class PhotoDetailRepositoryImpl(
    private val photosRemoteDataSource: PhotosRemoteDataSource
): PhotoDetailRepository{
    override suspend fun getPhoto(id: Long): PhotoDN {
        return photosRemoteDataSource.getPhoto(id).toPhotoDN()
    }
}