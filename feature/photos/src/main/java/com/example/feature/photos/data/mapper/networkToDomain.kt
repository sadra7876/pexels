package com.example.feature.photos.data.mapper

import com.example.network.dto.response.Curated.PhotoResponseDto
import com.example.network.dto.response.Curated.PhotoSrcResponseDto

fun PhotoSrcResponseDto.toPhotoSrcDN() : com.example.feature.photos.domain.models.PhotoSrcDN {
    return com.example.feature.photos.domain.models.PhotoSrcDN(
        original = this.original,
        medium = this.medium
    )
}

fun PhotoResponseDto.toPhotoDN() : com.example.feature.photos.domain.models.PhotoDN {
    return com.example.feature.photos.domain.models.PhotoDN(
        id = this.id,
        src = this.src.toPhotoSrcDN()
    )
}
