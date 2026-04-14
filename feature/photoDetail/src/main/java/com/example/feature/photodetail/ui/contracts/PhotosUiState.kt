package com.example.feature.photodetail.ui.contracts

import com.example.core.sharedmodel.dn.PhotoDN


sealed class PhotoDetailUiState {
    data object Loading : PhotoDetailUiState()
    data class Success(val photo: PhotoDN,) : PhotoDetailUiState()
    data class Error(val message: String) : PhotoDetailUiState()
}