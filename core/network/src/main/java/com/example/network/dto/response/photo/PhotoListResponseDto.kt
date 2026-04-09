package com.example.network.dto.response.photo

import kotlinx.serialization.Serializable

@Serializable
data class PhotoListResponseDto (
    val id: Long,
    val src: PhotoListSrcResponseDto
)