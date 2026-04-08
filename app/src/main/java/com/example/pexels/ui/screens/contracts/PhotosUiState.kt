package com.example.pexels.ui.screens.contracts

import com.example.pexels.domain.models.PhotoDN

sealed class PhotosUiState {
    object Idle : PhotosUiState()
    object Loading : PhotosUiState()
    data class Success(val photos: List<PhotoDN>) : PhotosUiState()
    data class Error(val message: String) : PhotosUiState()
}