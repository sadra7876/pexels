package com.example.feature.photos.domain.usecases

import com.example.feature.photos.domain.models.PhotoDN

interface GetPhotosUseCase {
    suspend operator fun invoke(
        page: Int,
        perPage: Int
    ): List<PhotoDN>
}