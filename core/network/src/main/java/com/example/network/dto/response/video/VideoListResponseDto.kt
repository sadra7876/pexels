package com.example.network.dto.response.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoListResponseDto (
    val id: Long,
    val image: String,
    val duration: Int,
    @SerialName("video_files")
    val videoFiles: List<VideoFileResponseDto>,

)