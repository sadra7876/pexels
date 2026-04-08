package com.example.feature.photos.ui.contracts

sealed class PhotosUiState {
    data object Idle : PhotosUiState()
    data object Loading : PhotosUiState()
    data class Success(val photos: List<com.example.feature.photos.domain.models.PhotoDN>) : PhotosUiState()
    data class Error(val message: String) : PhotosUiState()
}