package com.example.feature.photodetail.data.repository

import com.example.core.database.dao.FavoriteDao
import com.example.core.database.entities.FavoritePhotoEntity
import com.example.feature.photodetail.data.mapper.toPhotoDN
import com.example.feature.photodetail.domain.models.PhotoDN
import com.example.feature.photodetail.domain.repository.PhotoDetailRepository
import com.example.network.networkCalls.photo.PhotosRemoteDataSource

internal class PhotoDetailRepositoryImpl(
    private val photosRemoteDataSource: PhotosRemoteDataSource,
    private val favoriteDao: FavoriteDao
): PhotoDetailRepository{
    override suspend fun getPhoto(id: Long): PhotoDN {
        return photosRemoteDataSource.getPhoto(id).toPhotoDN()
    }

    override suspend fun addToFavorite(photoId: Long) {
        favoriteDao.addToFavorite(FavoritePhotoEntity(photoId = photoId))
    }

    override suspend fun removeFromFavorite(photoId: Long) {
        favoriteDao.removeFromFavorite(photoId)
    }

    override suspend fun isFavorite(photoId: Long): Boolean {
        return favoriteDao.isFavorite(photoId)
    }
}