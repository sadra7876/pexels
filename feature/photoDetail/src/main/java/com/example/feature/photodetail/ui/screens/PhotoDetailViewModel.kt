package com.example.feature.photodetail.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature.photodetail.domain.usecases.api.FavoritePhotoUseCase
import com.example.feature.photodetail.domain.usecases.api.GetPhotoUseCase
import com.example.feature.photodetail.ui.contracts.PhotoDetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PhotoDetailViewModel(
    private val getPhotoUseCase: GetPhotoUseCase,
    private val favoritePhotoUseCase: FavoritePhotoUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<PhotoDetailUiState>(PhotoDetailUiState.Loading)
    val uiState: StateFlow<PhotoDetailUiState> = _uiState

    fun loadPhoto(id: Long) {
        viewModelScope.launch {
            _uiState.value = PhotoDetailUiState.Loading

            try {
                getPhotoUseCase(id).collect{
                    _uiState.value = PhotoDetailUiState.Success(it)
                }
            } catch (e: Exception) {
                _uiState.value =
                    PhotoDetailUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun toggleFavorite(state: PhotoDetailUiState.Success) {
        viewModelScope.launch {
            if (!state.photo.isFavorite) {
                favoritePhotoUseCase.addToFavorite(state.photo.id)
            } else {
                favoritePhotoUseCase.removeFromFavorite(state.photo.id)
            }
        }

    }
}