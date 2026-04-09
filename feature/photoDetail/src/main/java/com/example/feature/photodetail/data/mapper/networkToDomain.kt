package com.example.feature.photodetail.data.mapper

import com.example.feature.photodetail.domain.models.PhotoDN
import com.example.feature.photodetail.domain.models.PhotoSrcDN
import com.example.network.dto.response.photo.PhotoResponseDto
import com.example.network.dto.response.photo.PhotoSrcResponseDto

fun PhotoSrcResponseDto.toPhotoSrcDN() : PhotoSrcDN {
    return PhotoSrcDN(
        original = this.original,
        medium = this.medium
    )
}

fun PhotoResponseDto.toPhotoDN() : PhotoDN {
    return PhotoDN(
        id = this.id,
        width = this.width,
        height = this.height,
        src = this.src.toPhotoSrcDN()
    )
}
