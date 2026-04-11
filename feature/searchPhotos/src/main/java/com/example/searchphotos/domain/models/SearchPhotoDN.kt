package com.example.searchphotos.domain.models

data class SearchPhotoDN (
    val id: Long,
    val width: Int,
    val height: Int,
    val src: SearchPhotoSrcDN
)