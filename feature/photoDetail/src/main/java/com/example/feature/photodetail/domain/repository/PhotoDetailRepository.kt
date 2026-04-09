package com.example.feature.photodetail.domain.repository

import com.example.feature.photodetail.domain.models.PhotoDN

interface PhotoDetailRepository {
    suspend fun getPhoto(
        id: Long
    ): PhotoDN
}