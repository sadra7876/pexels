package com.example.core.mapper

import com.example.core.database.entities.PhotoEntity
import com.example.core.database.entities.dbo.PhotoSrcDbo
import com.example.core.sharedmodel.dn.PhotoDN
import com.example.core.sharedmodel.dn.PhotoSrcDN
import com.example.network.dto.response.photo.PhotoSrcResponseDto

fun PhotoDN.toPhotoEntity(page: Int): PhotoEntity {
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

fun PhotoSrcDN.toPhotoSrcDbo(): PhotoSrcDbo {
    return PhotoSrcDbo(
        large = large,
        original = original,
        medium = medium,
        small = small,
        tiny = tiny
    )
}
