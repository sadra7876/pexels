package com.example.network.dto.response.Curated

import kotlinx.serialization.Serializable

@Serializable
data class PhotoSrcResponseDto (
    val original: String,
    val medium : String
)