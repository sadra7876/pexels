package com.example.feature.photos.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.errorhandling.ApiExceptions
import com.example.feature.photos.ui.contracts.PhotosUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PhotosViewModel(
    private val getPhotosUseCase: com.example.feature.photos.domain.usecases.GetPhotosUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<PhotosUiState>(PhotosUiState.Idle)
    val uiState: StateFlow<PhotosUiState> = _uiState

    fun loadPhotos(page: Int, perPage: Int) {
        viewModelScope.launch {
            _uiState.value = PhotosUiState.Loading

            try {
                val result = getPhotosUseCase(page, perPage)
                _uiState.value = PhotosUiState.Success(result)
            } catch (e: ApiExceptions) {
                _uiState.value = PhotosUiState.Error(
                    e.message
                )
            }
        }
    }
}