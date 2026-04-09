package com.example.feature.photos.data.mapper

import com.example.feature.photos.domain.models.PhotoListDN
import com.example.feature.photos.domain.models.PhotoListSrcDN
import com.example.network.dto.response.photo.PhotoListResponseDto
import com.example.network.dto.response.photo.PhotoListSrcResponseDto

fun PhotoListSrcResponseDto.toPhotoListSrcDN() : PhotoListSrcDN {
    return PhotoListSrcDN(
        original = this.original,
        medium = this.medium
    )
}

fun PhotoListResponseDto.toPhotoListDN() : PhotoListDN {
    return PhotoListDN(
        id = this.id,
        src = this.src.toPhotoListSrcDN()
    )
}
