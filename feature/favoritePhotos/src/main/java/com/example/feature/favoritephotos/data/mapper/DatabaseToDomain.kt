package com.example.feature.favoritephotos.data.mapper

import com.example.core.database.entities.dbo.PhotoSrcDbo
import com.example.core.database.relations.FavoritePhotoWithDetails
import com.example.feature.favoritephotos.domain.models.FavoritePhotoDN
import com.example.feature.favoritephotos.domain.models.FavoritePhotoListSrcDN

fun FavoritePhotoWithDetails.toFavoritePhotoDN() : FavoritePhotoDN {
    return FavoritePhotoDN(
        id = photo.id,
        width = photo.width,
        height = photo.height,
        src = photo.src.toFavoritePhotoListSrcDN(),
        likedAt = favorite.likedAt
    )
}

fun PhotoSrcDbo.toFavoritePhotoListSrcDN() : FavoritePhotoListSrcDN {
    return FavoritePhotoListSrcDN(
        original = original,
        medium = medium,
        small = small,
        tiny = tiny
    )
}