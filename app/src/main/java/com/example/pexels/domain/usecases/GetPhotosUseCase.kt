package com.example.pexels.domain.usecases

import com.example.pexels.domain.models.PhotoDN

interface GetPhotosUseCase {
    suspend operator fun invoke(
        page: Int,
        perPage: Int
    ): List<PhotoDN>
}