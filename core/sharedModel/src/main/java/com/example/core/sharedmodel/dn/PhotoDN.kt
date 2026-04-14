package com.example.core.sharedmodel.dn

data class PhotoDN (
    val id: Long,
    val avgColor: String,
    val width: Int,
    val height: Int,
    val src: PhotoSrcDN,
    val photographer: String,
    val alt: String,
    val isFavorite: Boolean
)