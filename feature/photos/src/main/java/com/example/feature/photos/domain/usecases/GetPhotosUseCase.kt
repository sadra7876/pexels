package com.example.feature.photos.domain.usecases

import androidx.paging.PagingData
import com.example.feature.photos.domain.models.PhotoListDN
import kotlinx.coroutines.flow.Flow

interface GetPhotosUseCase {
    operator fun invoke(): Flow<PagingData<PhotoListDN>>
}