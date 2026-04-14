package com.example.searchphotos.data.mapper

import com.example.core.database.relations.SearchHistoryWithDetails
import com.example.core.mapper.toPhotoSrcDN
import com.example.core.sharedmodel.dn.PhotoDN

fun SearchHistoryWithDetails.toPhotoDN() : PhotoDN {
    return PhotoDN(
        id = photo.id,
        avgColor = photo.avgColor,
        width = photo.width,
        height = photo.height,
        src = photo.src.toPhotoSrcDN(),
        alt = photo.alt,
        isFavorite = false,
        photographer = photo.photographer
    )
}