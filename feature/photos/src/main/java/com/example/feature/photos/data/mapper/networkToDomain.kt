package com.example.feature.photos.data.mapper

import com.example.feature.photos.domain.models.PhotoListDN
import com.example.feature.photos.domain.models.PhotoListSrcDN
import com.example.network.dto.response.photo.PhotoListResponseDto
import com.example.network.dto.response.photo.PhotoListSrcResponseDto

fun PhotoListSrcResponseDto.toPhotoListSrcDN() : PhotoListSrcDN {
    return PhotoListSrcDN(
        original = original,
        medium = medium,
        small = small,
        tiny = tiny
    )
}

fun PhotoListResponseDto.toPhotoListDN() : PhotoListDN {
    return PhotoListDN(
        id = id,
        width = width,
        height = height,
        src = src.toPhotoListSrcDN(),
        isFavorite = false
    )
}
