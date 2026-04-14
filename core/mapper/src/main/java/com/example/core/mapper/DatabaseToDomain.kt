package com.example.core.mapper

import com.example.core.database.entities.PhotoEntity
import com.example.core.database.entities.dbo.PhotoSrcDbo
import com.example.core.sharedmodel.dn.PhotoDN
import com.example.core.sharedmodel.dn.PhotoSrcDN

fun  PhotoEntity.toPhotoDN(page: Int): PhotoDN {
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

fun  PhotoSrcDbo.toPhotoSrcDN(): PhotoSrcDN {
    return PhotoSrcDN(
        large = large,
        original = original,
        medium = medium,
        small = small,
        tiny = tiny
    )
}
