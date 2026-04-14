package com.example.searchphotos.ui.contracts

import com.example.core.sharedmodel.dn.PhotoDN


data class SearchPhotosUiState(
    val query: String = "",
    val photos: List<PhotoDN> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val page: Int = 1,
    val hasNextPage: Boolean = true,
    val error: String? = null,
    val history: List<PhotoDN> = emptyList()
)