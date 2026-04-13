package com.example.searchphotos.data.mapper

import com.example.network.dto.response.photo.PhotoListResponseDto
import com.example.network.dto.response.photo.PhotoSrcResponseDto
import com.example.searchphotos.domain.models.SearchPhotoDN
import com.example.searchphotos.domain.models.SearchPhotoSrcDN

fun PhotoSrcResponseDto.toSearchPhotoSrcDN() : SearchPhotoSrcDN {
    return SearchPhotoSrcDN(
        original = original,
        medium = medium
    )
}

fun PhotoListResponseDto.toSearchPhotoDN() : SearchPhotoDN {
    return SearchPhotoDN(
        id = id,
        width = width,
        height = height,
        src = src.toSearchPhotoSrcDN()
    )
}
