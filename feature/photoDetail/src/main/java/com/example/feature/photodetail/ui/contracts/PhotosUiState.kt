package com.example.feature.photodetail.ui.contracts

import com.example.feature.photodetail.domain.models.PhotoDN

sealed class PhotoDetailUiState {
    object Loading : PhotoDetailUiState()
    data class Success(val photo: PhotoDN) : PhotoDetailUiState()
    data class Error(val message: String) : PhotoDetailUiState()
}