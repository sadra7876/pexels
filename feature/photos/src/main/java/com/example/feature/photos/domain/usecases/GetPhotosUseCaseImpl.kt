package com.example.feature.photos.domain.usecases

import com.example.feature.photos.domain.models.PhotoDN
import com.example.feature.photos.domain.repository.FirstPageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class GetPhotosUseCaseImpl (
    private val repository: FirstPageRepository
) : GetPhotosUseCase {
    override suspend fun invoke(page: Int, perPage: Int): List<PhotoDN> =
        withContext(Dispatchers.IO) {
            repository.getPhotos(page, perPage)
        }
}