package com.example.searchphotos.domain.usecases.api

import com.example.core.sharedmodel.dn.PhotoDN


interface SearchPhotosUseCase {
    suspend operator fun invoke(
        query: String,
        page: Int,
        perPage: Int,
    ): List<PhotoDN>
}