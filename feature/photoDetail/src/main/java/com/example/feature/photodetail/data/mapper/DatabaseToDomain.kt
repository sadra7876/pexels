package com.example.feature.photodetail.data.mapper

import com.example.core.database.relations.PhotoWithFavorite
import com.example.core.mapper.toPhotoSrcDN
import com.example.core.sharedmodel.dn.PhotoDN


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
