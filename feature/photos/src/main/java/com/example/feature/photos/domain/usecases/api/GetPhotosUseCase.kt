package com.example.feature.photos.domain.usecases.api

import androidx.paging.PagingData
import com.example.core.sharedmodel.dn.PhotoDN
import kotlinx.coroutines.flow.Flow

interface GetPhotosUseCase {
    operator fun invoke(): Flow<PagingData<PhotoDN>>
}