package com.example.feature.photos.data.mapper

import com.example.core.database.relations.PhotoWithFavorite
import com.example.core.mapper.toPhotoSrcDN
import com.example.core.sharedmodel.dn.PhotoDN

fun PhotoWithFavorite.toPhotoDN() : PhotoDN {
    return PhotoDN(
        id = photo.id,
        width = photo.width,
        height = photo.height,
        src = photo.src.toPhotoSrcDN(),
        isFavorite = isFavorite,
        avgColor = photo.avgColor,
        photographer = photo.photographer,
        alt = photo.alt
    )
}
