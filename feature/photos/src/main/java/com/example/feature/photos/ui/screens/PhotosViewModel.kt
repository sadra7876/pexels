package com.example.feature.photos.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.core.database.relations.PhotoWithFavorite
import com.example.core.sharedmodel.dn.PhotoDN
import com.example.feature.photodetail.domain.usecases.api.FavoritePhotoUseCase
import com.example.feature.photos.domain.usecases.api.GetPhotosUseCase
import com.example.feature.photos.domain.usecases.api.DarkModeUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class PhotosViewModel(
    private val getPhotosUseCase: GetPhotosUseCase,
    private val darkModeUseCase: DarkModeUseCase,
    private val favoritePhotoUseCase: FavoritePhotoUseCase
) : ViewModel() {

    val photos = getPhotosUseCase()
        .cachedIn(viewModelScope)

    fun updateDarkMode(toDark: Boolean) {
        viewModelScope.launch {
            darkModeUseCase.updateDarkMode(toDark)
        }

    }
    fun toggleFavorite( photo : PhotoDN ) {
        viewModelScope.launch {
            if (!photo.isFavorite) {
                favoritePhotoUseCase.addToFavorite(photo.id)
            } else {
                favoritePhotoUseCase.removeFromFavorite(photo.id)
            }
        }
    }
    val isDarkMode: Flow<Boolean> = darkModeUseCase.getDarkMode()
}