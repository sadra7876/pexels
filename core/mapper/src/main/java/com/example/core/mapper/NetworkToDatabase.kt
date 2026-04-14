package com.example.core.mapper

import com.example.core.database.entities.PhotoEntity
import com.example.core.database.entities.dbo.PhotoSrcDbo
import com.example.network.dto.response.photo.PhotoResponseDto
import com.example.network.dto.response.photo.PhotoSrcResponseDto

fun PhotoResponseDto.toPhotoEntity(page: Int): PhotoEntity {
    return PhotoEntity(
        id = id,
        avgColor = avgColor,
        width = width,
        height = height,
        src = src.toPhotoSrcDbo(),
        photographer = photographer,
        alt =  alt,
        page = page
    )
}

fun PhotoSrcResponseDto.toPhotoSrcDbo(): PhotoSrcDbo {
    return PhotoSrcDbo(
        large = large,
        original = original,
        medium = medium,
        small = small,
        tiny = tiny
    )
}
