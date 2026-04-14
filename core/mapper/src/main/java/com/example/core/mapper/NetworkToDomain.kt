package com.example.core.mapper

import com.example.core.sharedmodel.dn.PhotoDN
import com.example.core.sharedmodel.dn.PhotoSrcDN
import com.example.network.dto.response.photo.PhotoResponseDto
import com.example.network.dto.response.photo.PhotoSrcResponseDto

fun PhotoResponseDto.toPhotoDN(): PhotoDN {
    return PhotoDN(
        id = id,
        avgColor = avgColor,
        width = width,
        height = height,
        src = src.toPhotoSrcDN(),
        photographer = photographer,
        alt =  alt,
        isFavorite = false
    )
}

fun PhotoSrcResponseDto.toPhotoSrcDN(): PhotoSrcDN {
    return PhotoSrcDN(
        large = large,
        original = original,
        medium = medium,
        small = small,
        tiny = tiny
    )
}
