package com.example.searchphotos.domain.usecases.impl

import com.example.core.sharedmodel.dn.PhotoDN
import com.example.searchphotos.domain.repository.SearchPhotoRepository
import com.example.searchphotos.domain.usecases.api.SearchHistoryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

internal class SearchHistoryUseCaseImpl(
    private val repository: SearchPhotoRepository
) : SearchHistoryUseCase {
    override fun getSearchHistory(): Flow<List<PhotoDN>> {
        return repository.getSearchHistory().flowOn(Dispatchers.IO)
    }

    override suspend fun addToHistory(photo: PhotoDN) {
        withContext(Dispatchers.IO) {
            repository.addToHistory(photo)
        }
    }

    override suspend fun deleteFromHistory(photoId: Long) {
        withContext(Dispatchers.IO) {
            repository.deleteFromHistory(photoId)
        }
    }

    override suspend fun clearHistory() {
        withContext(Dispatchers.IO) {
            repository.clearHistory()
        }

    }
}