package com.example.feature.photodetail.domain.usecases.api

import com.example.feature.photodetail.domain.models.PhotoDN
import kotlinx.coroutines.flow.Flow

interface GetPhotoUseCase {
    operator fun invoke(
        id: Long
    ): Flow<PhotoDN>
}