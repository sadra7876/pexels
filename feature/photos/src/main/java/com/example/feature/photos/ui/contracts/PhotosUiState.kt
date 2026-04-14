package com.example.feature.photos.ui.contracts

import com.example.core.sharedmodel.dn.PhotoDN


data class PhotosUiState(
    val photos: List<PhotoDN> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val page: Int = 1,
    val endReached: Boolean = false,
    val error: String? = null
)