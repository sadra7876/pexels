package com.example.core.navigation.viewModelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feature.favoritephotos.domain.usecases.GetFavoritePhotosUseCase
import com.example.feature.favoritephotos.ui.FavoritePhotosViewModel

class FavoritePhotosViewModelFactory(
    private val getFavoritePhotosUseCase: GetFavoritePhotosUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoritePhotosViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoritePhotosViewModel(getFavoritePhotosUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}