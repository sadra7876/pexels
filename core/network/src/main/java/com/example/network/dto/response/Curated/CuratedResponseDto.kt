package com.example.network.dto.response.Curated

import kotlinx.serialization.Serializable

@Serializable
data class CuratedResponseDto(
        val photos: List<PhotoResponseDto>
)