package com.example.feature.photos.domain.repository

import com.example.feature.photos.domain.models.PhotoDN

interface FirstPageRepository {
    suspend fun getPhotos(
        page: Int,
        perPage: Int
    ): List<PhotoDN>
}