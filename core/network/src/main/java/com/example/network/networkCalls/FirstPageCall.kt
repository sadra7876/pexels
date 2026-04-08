package com.example.network.networkCalls

import com.example.network.dto.response.Curated.CuratedResponseDto

interface FirstPageCall {
    suspend fun getCuratedPhotos(
        page: Int,
        perPage: Int,
    ): CuratedResponseDto
}