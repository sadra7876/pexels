package com.example.network.dto.response.photo

import kotlinx.serialization.Serializable

@Serializable
data class PhotoSrcResponseDto (
    val original: String,
    val medium : String
)