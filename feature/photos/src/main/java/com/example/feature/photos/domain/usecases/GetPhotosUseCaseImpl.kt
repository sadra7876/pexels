package com.example.feature.photos.domain.usecases

import androidx.paging.PagingData
import com.example.core.sharedmodel.dn.PhotoDN
import com.example.feature.photos.domain.repository.PhotosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

internal class GetPhotosUseCaseImpl (
    private val repository: PhotosRepository
) : GetPhotosUseCase {
    override fun invoke(): Flow<PagingData<PhotoDN>> =
            repository.getPhotos().flowOn(Dispatchers.IO)


}