package com.example.network.dto.response.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoFileResponseDto (
    val id: Int,
    @SerialName("file_type")
    val fileType: String,
    val width: Int,
    val height: Int,
    val link: String
)