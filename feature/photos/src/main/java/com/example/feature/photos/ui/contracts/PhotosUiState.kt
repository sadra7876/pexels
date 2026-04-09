package com.example.feature.photos.ui.contracts

import com.example.feature.photos.domain.models.PhotoListDN

data class PhotosUiState(
    val photos: List<PhotoListDN> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val page: Int = 1,
    val endReached: Boolean = false,
    val error: String? = null
)