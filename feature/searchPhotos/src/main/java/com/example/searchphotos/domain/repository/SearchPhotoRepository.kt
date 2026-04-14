package com.example.searchphotos.domain.repository

import com.example.core.sharedmodel.dn.PhotoDN
import kotlinx.coroutines.flow.Flow


interface SearchPhotoRepository {
    suspend fun searchPhotos(
        query: String,
        page: Int,
        perPage: Int,
    ): List<PhotoDN>

    fun getSearchHistory(): Flow<List<PhotoDN>>

    suspend fun addToHistory( photo: PhotoDN)

    suspend fun deleteFromHistory( photoId: Long)

    suspend fun clearHistory()
}