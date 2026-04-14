package com.example.core.navigation.viewModelFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.searchphotos.domain.usecases.api.SearchHistoryUseCase
import com.example.searchphotos.domain.usecases.api.SearchPhotosUseCase
import com.example.searchphotos.ui.screens.SearchPhotosViewModel

class SearchPhotosViewModelFactory(
    private val searchPhotosUseCase : SearchPhotosUseCase,
    private val searchHistoryUseCase : SearchHistoryUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchPhotosViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchPhotosViewModel(
                searchPhotosUseCase = searchPhotosUseCase,
                searchHistoryUseCase = searchHistoryUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}