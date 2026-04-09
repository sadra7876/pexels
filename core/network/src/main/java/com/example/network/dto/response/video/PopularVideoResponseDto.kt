package com.example.network.dto.response.video

import kotlinx.serialization.Serializable

@Serializable
data class PopularVideoResponseDto (
    val videos: List<VideoFileResponseDto>
)