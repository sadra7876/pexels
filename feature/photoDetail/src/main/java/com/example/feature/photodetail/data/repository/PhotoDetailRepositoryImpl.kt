package com.example.feature.photodetail.data.repository

import android.util.Log
import com.example.core.database.dao.FavoriteDao
import com.example.core.database.dao.PhotoDao
import com.example.core.database.entities.FavoritePhotoEntity
import com.example.core.mapper.toPhotoEntity
import com.example.feature.photodetail.data.mapper.toPhotoDN
import com.example.feature.photodetail.domain.models.PhotoDN
import com.example.feature.photodetail.domain.repository.PhotoDetailRepository
import com.example.network.networkCalls.photo.PhotosRemoteDataSource
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.supervisorScope

private val Tag = "PhotoDetailRepositoryImpl"

internal class PhotoDetailRepositoryImpl(
    private val photosRemoteDataSource: PhotosRemoteDataSource,
    private val favoriteDao: FavoriteDao,
    private val photoDao: PhotoDao
): PhotoDetailRepository{
    override fun getPhoto(id: Long): Flow<PhotoDN> = flow {
        try {
            supervisorScope {
                val asyncUpdatePhoto = async {
                    try {
                        val photo = photosRemoteDataSource.getPhoto(id)
                        photoDao.insertPhoto(photo.toPhotoEntity(1))
                    } catch (e: Exception) {
                        Log.e(Tag, e.message ?: "asyncUpdatePhoto")
                        throw e
                    }
                }

                photoDao.getPhotoWithFavorite(id).mapNotNull {
                    it?.toPhotoDN()
                }.collect {
                    emit(it)

                }

                asyncUpdatePhoto.await()
            }

        } catch (e: Exception) {
            Log.e(Tag, e.message ?: "getPhotoSupervisorScope")
            throw e
        }

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