package com.example.feature.photodetail.domain.repository

import com.example.feature.photodetail.domain.models.PhotoDN
import kotlinx.coroutines.flow.Flow

interface PhotoDetailRepository {
    fun getPhoto(
        id: Long
    ): Flow<PhotoDN>

    suspend fun addToFavorite(photoId: Long)

    suspend fun removeFromFavorite(photoId: Long)

    suspend fun isFavorite(photoId: Long): Boolean
}