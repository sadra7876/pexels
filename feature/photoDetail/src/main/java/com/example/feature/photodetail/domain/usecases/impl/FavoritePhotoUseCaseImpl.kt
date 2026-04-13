package com.example.feature.photodetail.domain.usecases.impl

import com.example.feature.photodetail.domain.repository.PhotoDetailRepository
import com.example.feature.photodetail.domain.usecases.api.FavoritePhotoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class FavoritePhotoUseCaseImpl (
    private val repository: PhotoDetailRepository
) : FavoritePhotoUseCase{
    override suspend fun addToFavorite(photoId: Long) {
        withContext(Dispatchers.IO) {
            repository.addToFavorite(photoId)

        }
    }

    override suspend fun removeFromFavorite(photoId: Long) {

        withContext(Dispatchers.IO) {
            repository.removeFromFavorite(photoId)
        }
    }

    override suspend fun isFavorite(photoId: Long): Boolean {

       return withContext(Dispatchers.IO) {
            repository.isFavorite(photoId)
        }
    }


}