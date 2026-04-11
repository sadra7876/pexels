package com.example.network.dto.response.photo

import kotlinx.serialization.Serializable

@Serializable
data class SearchResponseDto(
    val photos: List<PhotoListResponseDto>
)