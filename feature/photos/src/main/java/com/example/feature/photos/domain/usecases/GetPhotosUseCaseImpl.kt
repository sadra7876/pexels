package com.example.feature.photos.domain.usecases

import com.example.feature.photos.domain.models.PhotoListDN
import com.example.feature.photos.domain.repository.PhotosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class GetPhotosUseCaseImpl (
    private val repository: PhotosRepository
) : GetPhotosUseCase {
    override suspend fun invoke(page: Int, perPage: Int): List<PhotoListDN> =
        withContext(Dispatchers.IO) {
            repository.getPhotos(page, perPage)
        }
}