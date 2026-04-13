package com.example.feature.photodetail.data.mapper

import com.example.core.database.entities.dbo.PhotoSrcDbo
import com.example.core.database.relations.PhotoWithFavorite
import com.example.feature.photodetail.domain.models.PhotoDN
import com.example.feature.photodetail.domain.models.PhotoSrcDN

fun PhotoSrcDbo.toPhotoSrcDN() : PhotoSrcDN {
    return PhotoSrcDN(
        original = original,
        medium = medium,
        large = large
    )
}

fun PhotoWithFavorite.toPhotoDN() : PhotoDN {
    return PhotoDN(
        id = photo.id,
        avgColor = photo.avgColor,
        width = photo.width,
        height = photo.height,
        src = photo.src.toPhotoSrcDN(),
        photographer = photo.photographer,
        alt = photo.alt,
        isFavorite = isFavorite
    )
}
