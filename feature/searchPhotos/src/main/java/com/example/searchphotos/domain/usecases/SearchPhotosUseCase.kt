package com.example.searchphotos.domain.usecases

import com.example.searchphotos.domain.models.SearchPhotoDN

interface SearchPhotosUseCase {
    suspend operator fun invoke(
        query: String,
        page: Int,
        perPage: Int,
    ): List<SearchPhotoDN>
}