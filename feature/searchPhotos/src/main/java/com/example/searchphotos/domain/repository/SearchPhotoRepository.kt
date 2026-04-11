package com.example.searchphotos.domain.repository

import com.example.searchphotos.domain.models.SearchPhotoDN


interface SearchPhotoRepository {
    suspend fun searchPhotos(
        query: String,
        page: Int,
        perPage: Int,
    ): List<SearchPhotoDN>
}