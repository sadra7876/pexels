package com.example.feature.photos.data.repository

import com.example.network.networkCalls.photo.PhotosRemoteDataSource
import com.example.feature.photos.data.mapper.toPhotoListDN

internal class PhotosRepositoryImpl(
    private val photosRemoteDataSource: PhotosRemoteDataSource
): com.example.feature.photos.domain.repository.PhotosRepository {
    override suspend fun getPhotos(page: Int, perPage: Int): List<com.example.feature.photos.domain.models.PhotoListDN> {
        return photosRemoteDataSource.getCuratedPhotos(page, perPage).photos.map { it.toPhotoListDN() }
    }
}