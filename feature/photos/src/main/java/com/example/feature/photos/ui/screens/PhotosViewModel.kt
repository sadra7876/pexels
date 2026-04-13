package com.example.feature.photos.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.feature.photos.domain.usecases.GetPhotosUseCase


class PhotosViewModel(
    private val getPhotosUseCase: GetPhotosUseCase
) : ViewModel() {

    val photos = getPhotosUseCase()
        .cachedIn(viewModelScope)
}