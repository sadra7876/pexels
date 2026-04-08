package com.example.network.dto.response.Curated

import kotlinx.serialization.Serializable

@Serializable
data class PhotoResponseDto (
    val id: Long,
    val src: PhotoSrcResponseDto
)