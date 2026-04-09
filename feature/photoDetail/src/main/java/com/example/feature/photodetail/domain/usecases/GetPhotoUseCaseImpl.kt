package com.example.feature.photodetail.domain.usecases

import com.example.feature.photodetail.domain.models.PhotoDN
import com.example.feature.photodetail.domain.repository.PhotoDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class GetPhotoUseCaseImpl (
    private val repository: PhotoDetailRepository
) : GetPhotoUseCase {
    override suspend fun invoke(id: Long): PhotoDN =
        withContext(Dispatchers.IO) {
            repository.getPhoto(id)
        }
}