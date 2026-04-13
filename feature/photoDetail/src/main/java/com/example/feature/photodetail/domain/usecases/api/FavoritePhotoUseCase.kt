package com.example.feature.photodetail.domain.usecases.api

interface FavoritePhotoUseCase {
     suspend fun addToFavorite(photoId: Long)
     suspend fun removeFromFavorite(photoId: Long)
     suspend fun isFavorite(photoId: Long): Boolean
}