package com.example.feature.favoritephotos.domain.models

data class FavoritePhotoListDN (
    val id: Long,
    val width: Int,
    val height: Int,
    val src: FavoritePhotoListSrcDN,
    val likedAt: Long
)