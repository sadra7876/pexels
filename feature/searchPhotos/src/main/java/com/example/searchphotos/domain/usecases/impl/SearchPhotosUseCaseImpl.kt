package com.example.searchphotos.domain.usecases.impl

import com.example.core.sharedmodel.dn.PhotoDN
import com.example.searchphotos.domain.repository.SearchPhotoRepository
import com.example.searchphotos.domain.usecases.api.SearchPhotosUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchPhotosUseCaseImpl(
    private val repository: SearchPhotoRepository
) : SearchPhotosUseCase {
    override suspend fun invoke(query: String, page: Int, perPage: Int): List<PhotoDN> {
        return withContext(Dispatchers.IO) {
            repository.searchPhotos(query, page, perPage)
        }
    }
}