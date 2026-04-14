package com.example.feature.photos.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.feature.photos.domain.usecases.api.GetPhotosUseCase
import com.example.feature.photos.domain.usecases.api.DarkModeUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class PhotosViewModel(
    private val getPhotosUseCase: GetPhotosUseCase,
    private val darkModeUseCase: DarkModeUseCase
) : ViewModel() {

    val photos = getPhotosUseCase()
        .cachedIn(viewModelScope)

    fun updateDarkMode(toDark: Boolean) {
        viewModelScope.launch {
            darkModeUseCase.updateDarkMode(toDark)
        }

    }

    val isDarkMode: Flow<Boolean> = darkModeUseCase.getDarkMode()
}