package com.example.pexels.data.mapper

import com.example.network.dto.response.Curated.PhotoResponseDto
import com.example.network.dto.response.Curated.PhotoSrcResponseDto
import com.example.pexels.domain.models.PhotoDN
import com.example.pexels.domain.models.PhotoSrcDN

fun PhotoSrcResponseDto.toPhotoSrcDN() : PhotoSrcDN {
    return PhotoSrcDN(
        original = this.original,
        medium = this.medium
    )
}

fun PhotoResponseDto.toPhotoDN() : PhotoDN {
    return PhotoDN(
        id = this.id,
        src = this.src.toPhotoSrcDN()
    )
}
