package com.example.pexels.domain.repository

import com.example.pexels.domain.models.PhotoDN

interface FirstPageRepository {
    suspend fun getPhotos(
        page: Int,
        perPage: Int
    ): List<PhotoDN>
}