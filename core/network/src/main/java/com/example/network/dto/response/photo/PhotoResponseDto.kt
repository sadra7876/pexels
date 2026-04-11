package com.example.network.dto.response.photo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoResponseDto (
    val id: Long,
    @SerialName("avg_color")
    val avgColor: String,
    val width: Int,
    val height: Int,
    val src: PhotoSrcResponseDto,
    val photographer: String,
    val alt: String,

)