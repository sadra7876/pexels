package com.example.core.navigation.viewModelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feature.photodetail.domain.usecases.GetPhotoUseCase
import com.example.feature.photodetail.ui.screens.PhotoDetailViewModel


class PhotoDetailViewModelFactory(
    private val getPhotoUseCase: GetPhotoUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhotoDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PhotoDetailViewModel(getPhotoUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}