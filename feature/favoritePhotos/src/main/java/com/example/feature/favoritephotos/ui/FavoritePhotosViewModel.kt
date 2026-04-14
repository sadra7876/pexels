package com.example.feature.favoritephotos.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.feature.favoritephotos.domain.usecases.GetFavoritePhotosUseCase
import com.example.feature.photodetail.domain.usecases.api.FavoritePhotoUseCase
import kotlinx.coroutines.launch

class FavoritePhotosViewModel(
    private val getFavoritePhotosUseCase: GetFavoritePhotosUseCase,
    private val favoritePhotoUseCase: FavoritePhotoUseCase
): ViewModel() {

    val favoritePhotos = getFavoritePhotosUseCase().cachedIn(viewModelScope)

    fun toggleFavorite( photoId : Long ) {
        viewModelScope.launch {
            favoritePhotoUseCase.removeFromFavorite(photoId)
        }
    }
}