package com.example.feature.photos.domain.models

data class PhotoListDN (
    val id: Long,
    val width: Int,
    val height: Int,
    val src: PhotoListSrcDN,
    val isFavorite: Boolean
)