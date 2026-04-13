package com.example.feature.favoritephotos.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.feature.favoritephotos.domain.usecases.GetFavoritePhotosUseCase

class FavoritePhotosViewModel(
    private val getFavoritePhotosUseCase: GetFavoritePhotosUseCase
): ViewModel() {

    val favoritePhotos = getFavoritePhotosUseCase().cachedIn(viewModelScope)
}