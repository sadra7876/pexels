package com.example.feature.photos.domain.repository

import com.example.feature.photos.domain.models.PhotoListDN

interface PhotosRepository {
    suspend fun getPhotos(
        page: Int,
        perPage: Int
    ): List<PhotoListDN>
}