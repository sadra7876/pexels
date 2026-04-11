package com.example.searchphotos.ui.contracts

import com.example.searchphotos.domain.models.SearchPhotoDN

data class SearchPhotosUiState(
    val query: String = "",
    val photos: List<SearchPhotoDN> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val page: Int = 1,
    val hasNextPage: Boolean = true,
    val error: String? = null
)