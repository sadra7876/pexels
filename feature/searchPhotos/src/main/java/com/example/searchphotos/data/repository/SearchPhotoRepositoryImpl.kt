package com.example.searchphotos.data.repository

import com.example.core.database.dao.PhotoDao
import com.example.core.database.dao.SearchHistoryDao
import com.example.core.database.entities.SearchHistoryEntity
import com.example.core.mapper.toPhotoDN
import com.example.core.mapper.toPhotoEntity
import com.example.core.sharedmodel.dn.PhotoDN
import com.example.network.networkCalls.photo.PhotosRemoteDataSource
import com.example.searchphotos.data.mapper.toPhotoDN
import com.example.searchphotos.domain.repository.SearchPhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class SearchPhotoRepositoryImpl(
    private val photosRemoteDataSource: PhotosRemoteDataSource,
    private val searchHistoryDao: SearchHistoryDao,
    private val photoDao: PhotoDao
) : SearchPhotoRepository {
    override suspend fun searchPhotos(query: String, page: Int, perPage: Int): List<PhotoDN> {
        return photosRemoteDataSource.searchPhotos(query, page, perPage).photos.map { it.toPhotoDN() }
    }

    override fun getSearchHistory(): Flow<List<PhotoDN>> {
        return searchHistoryDao.getHistory().map { it.map { it.toPhotoDN() } }
    }

    override suspend fun addToHistory(photo: PhotoDN) {
        photoDao.insertPhoto(photo.toPhotoEntity(1))
        searchHistoryDao.insert(SearchHistoryEntity(photoId = photo.id))
    }

    override suspend fun deleteFromHistory(photoId: Long) {
        searchHistoryDao.deleteByPhotoIdId(photoId)
    }

    override suspend fun clearHistory() {
        searchHistoryDao.clearAll()
    }
}