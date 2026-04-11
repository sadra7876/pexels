package com.example.feature.photodetail.data.mapper

import com.example.feature.photodetail.domain.models.PhotoDN
import com.example.feature.photodetail.domain.models.PhotoSrcDN
import com.example.network.dto.response.photo.PhotoResponseDto
import com.example.network.dto.response.photo.PhotoSrcResponseDto

fun PhotoSrcResponseDto.toPhotoSrcDN() : PhotoSrcDN {
    return PhotoSrcDN(
        original = original,
        medium = medium,
        large = large
    )
}

fun PhotoResponseDto.toPhotoDN() : PhotoDN {
    return PhotoDN(
        id = id,
        avgColor = avgColor,
        width = width,
        height = height,
        src = src.toPhotoSrcDN(),
        photographer = photographer,
        alt = alt
    )
}
