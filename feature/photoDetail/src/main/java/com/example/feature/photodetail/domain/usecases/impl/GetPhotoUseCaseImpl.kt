package com.example.feature.photodetail.domain.usecases.impl

import com.example.feature.photodetail.domain.models.PhotoDN
import com.example.feature.photodetail.domain.repository.PhotoDetailRepository
import com.example.feature.photodetail.domain.usecases.api.GetPhotoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

internal class GetPhotoUseCaseImpl (
    private val repository: PhotoDetailRepository
) : GetPhotoUseCase {
    override fun invoke(id: Long): Flow<PhotoDN> =
            repository.getPhoto(id).flowOn(Dispatchers.IO)
}