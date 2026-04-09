package com.example.feature.photos.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature.photos.domain.usecases.GetPhotosUseCase
import com.example.network.errorhandling.ApiExceptions
import com.example.feature.photos.ui.contracts.PhotosUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PhotosViewModel(
    private val getPhotosUseCase: GetPhotosUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PhotosUiState())
    val uiState: StateFlow<PhotosUiState> = _uiState

    fun loadFirstPage(perPage: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null, page = 1) }

            try {
                val photos = getPhotosUseCase(1, perPage)

                _uiState.update {
                    it.copy(
                        photos = photos,
                        isLoading = false,
                        page = 2,
                        endReached = photos.isEmpty()
                    )
                }
            } catch (e: ApiExceptions) {
                _uiState.update {
                    it.copy(isLoading = false, error = e.message)
                }
            }
        }
    }

    fun loadNextPage(perPage: Int) {
        val state = _uiState.value

        if (state.isLoadingMore || state.endReached) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoadingMore = true) }

            try {
                val newPhotos = getPhotosUseCase(state.page, perPage)

                _uiState.update {
                    it.copy(
                        photos = it.photos + newPhotos,
                        isLoadingMore = false,
                        page = it.page + 1,
                        endReached = newPhotos.isEmpty()
                    )
                }
            } catch (e: ApiExceptions) {
                _uiState.update {
                    it.copy(isLoadingMore = false, error = e.message)
                }
            }
        }
    }
}