package com.example.network.dto.response.photo

import kotlinx.serialization.Serializable

@Serializable
data class PhotoListSrcResponseDto (
    val original: String,
    val medium : String
)