package com.example.network.networkCalls.photo

import com.example.network.dto.response.photo.CuratedResponseDto
import com.example.network.dto.response.photo.PhotoResponseDto
import com.example.network.dto.response.photo.SearchResponseDto

interface PhotosRemoteDataSource {
    suspend fun getCuratedPhotos(
        page: Int,
        perPage: Int,
    ): CuratedResponseDto

    suspend fun getPhoto(
        id: Long
    ): PhotoResponseDto

    suspend fun searchPhotos(
        query: String,
        page: Int,
        perPage: Int,
    ): SearchResponseDto
}