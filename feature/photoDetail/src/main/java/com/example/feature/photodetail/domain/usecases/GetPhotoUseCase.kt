package com.example.feature.photodetail.domain.usecases

import com.example.feature.photodetail.domain.models.PhotoDN

interface GetPhotoUseCase {
    suspend operator fun invoke(
        id: Long
    ): PhotoDN
}