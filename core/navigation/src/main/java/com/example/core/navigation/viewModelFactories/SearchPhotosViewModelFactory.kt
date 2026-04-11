package com.example.core.navigation.viewModelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.searchphotos.domain.usecases.SearchPhotosUseCase
import com.example.searchphotos.ui.screens.SearchPhotosViewModel

class SearchPhotosViewModelFactory(
    private val searchPhotosUseCase: SearchPhotosUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchPhotosViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchPhotosViewModel(searchPhotosUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}