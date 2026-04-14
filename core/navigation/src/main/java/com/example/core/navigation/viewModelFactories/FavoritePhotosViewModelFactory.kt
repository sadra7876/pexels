package com.example.core.navigation.viewModelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feature.favoritephotos.domain.usecases.GetFavoritePhotosUseCase
import com.example.feature.favoritephotos.ui.FavoritePhotosViewModel
import com.example.feature.photodetail.domain.usecases.api.FavoritePhotoUseCase

class FavoritePhotosViewModelFactory(
    private val getFavoritePhotosUseCase: GetFavoritePhotosUseCase,
    private val favoritePhotoUseCase: FavoritePhotoUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoritePhotosViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoritePhotosViewModel(getFavoritePhotosUseCase, favoritePhotoUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}