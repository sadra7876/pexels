package com.example.searchphotos.domain.usecases.api

import com.example.core.sharedmodel.dn.PhotoDN
import kotlinx.coroutines.flow.Flow

interface SearchHistoryUseCase {

    fun getSearchHistory(): Flow<List<PhotoDN>>

    suspend fun addToHistory( photo: PhotoDN)

    suspend fun deleteFromHistory( photoId: Long)

    suspend fun clearHistory()
}