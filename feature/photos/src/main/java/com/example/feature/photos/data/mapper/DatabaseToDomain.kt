package com.example.feature.photos.data.mapper

import com.example.core.database.entities.dbo.PhotoSrcDbo
import com.example.core.database.relations.PhotoWithFavorite
import com.example.feature.photos.domain.models.PhotoListDN
import com.example.feature.photos.domain.models.PhotoListSrcDN

fun PhotoSrcDbo.toPhotoListSrcDN() : PhotoListSrcDN {
    return PhotoListSrcDN(
        original = original,
        medium = medium,
        small = small,
        tiny = tiny
    )
}

fun PhotoWithFavorite.toPhotoListDN() : PhotoListDN {
    return PhotoListDN(
        id = photo.id,
        width = photo.width,
        height = photo.height,
        src = photo.src.toPhotoListSrcDN(),
        isFavorite = isFavorite
    )
}
