package com.example.searchphotos.domain.usecases

import com.example.searchphotos.domain.models.SearchPhotoDN
import com.example.searchphotos.domain.repository.SearchPhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchPhotosUseCaseImpl(
    private val repository: SearchPhotoRepository
) : SearchPhotosUseCase  {
    override suspend fun invoke(query: String, page: Int, perPage: Int): List<SearchPhotoDN> {
        return withContext(Dispatchers.IO){
            repository.searchPhotos(query, page, perPage)
        }
    }
}