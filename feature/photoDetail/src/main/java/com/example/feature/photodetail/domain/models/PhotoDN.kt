package com.example.feature.photodetail.domain.models

data class PhotoDN (
    val id: Long,
    val avgColor: String,
    val width: Int,
    val height: Int,
    val src: PhotoSrcDN,
    val photographer: String,
    val alt: String,
)