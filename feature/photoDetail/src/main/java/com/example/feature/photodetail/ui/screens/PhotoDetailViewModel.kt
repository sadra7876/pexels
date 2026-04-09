package com.example.feature.photodetail.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature.photodetail.domain.usecases.GetPhotoUseCase
import com.example.feature.photodetail.ui.contracts.PhotoDetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PhotoDetailViewModel(
    private val getPhotoUseCase: GetPhotoUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<PhotoDetailUiState>(PhotoDetailUiState.Loading)
    val uiState: StateFlow<PhotoDetailUiState> = _uiState

    fun loadPhoto(id: Long) {
        viewModelScope.launch {
            _uiState.value = PhotoDetailUiState.Loading

            try {
                val photo = getPhotoUseCase(id)
                _uiState.value = PhotoDetailUiState.Success(photo)
            } catch (e: Exception) {
                _uiState.value =
                    PhotoDetailUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}