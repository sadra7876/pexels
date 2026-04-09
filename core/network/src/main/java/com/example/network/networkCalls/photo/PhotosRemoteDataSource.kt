package com.example.network.networkCalls.photo

import com.example.network.dto.response.photo.CuratedResponseDto
import com.example.network.dto.response.photo.PhotoResponseDto

interface PhotosRemoteDataSource {
    suspend fun getCuratedPhotos(
        page: Int,
        perPage: Int,
    ): CuratedResponseDto

    suspend fun getPhoto(
        id: Long
    ): PhotoResponseDto
}