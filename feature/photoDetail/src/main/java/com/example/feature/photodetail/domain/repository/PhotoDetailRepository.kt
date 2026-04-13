package com.example.feature.photodetail.domain.repository

import com.example.feature.photodetail.domain.models.PhotoDN

interface PhotoDetailRepository {
    suspend fun getPhoto(
        id: Long
    ): PhotoDN

    suspend fun addToFavorite(photoId: Long)

    suspend fun removeFromFavorite(photoId: Long)

    suspend fun isFavorite(photoId: Long): Boolean
}