package com.example.core.navigation.viewModelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feature.photos.domain.usecases.GetPhotosUseCase
import com.example.feature.photos.ui.screens.PhotosViewModel

class PhotosViewModelFactory(
    private val getPhotosUseCase: GetPhotosUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhotosViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PhotosViewModel(getPhotosUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}