package com.example.feature.photodetail.domain.usecases.api

import com.example.feature.photodetail.domain.models.PhotoDN

interface GetPhotoUseCase {
    suspend operator fun invoke(
        id: Long
    ): PhotoDN
}