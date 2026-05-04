package com.example.searchphotos.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.sharedmodel.dn.PhotoDN
import com.example.searchphotos.domain.usecases.api.SearchHistoryUseCase
import com.example.searchphotos.domain.usecases.api.SearchPhotosUseCase
import com.example.searchphotos.ui.contracts.SearchPhotosUiState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.String


class SearchPhotosViewModel(
    private val searchPhotosUseCase : SearchPhotosUseCase,
    private val searchHistoryUseCase : SearchHistoryUseCase
) : ViewModel() {

    private val queryFlow = MutableStateFlow("")

    private val _uiState = MutableStateFlow(SearchPhotosUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            searchHistoryUseCase.getSearchHistory().collect{ history ->
                _uiState.update {
                    it.copy( history = history )
                }
            }
        }
        observeQuery()
    }

    fun onPhotoClicked(photo: PhotoDN) {
        viewModelScope.launch {
            searchHistoryUseCase.addToHistory(photo)
        }
    }

    fun deleteHistoryItem(photoId: Long) {
        viewModelScope.launch {
            searchHistoryUseCase.deleteFromHistory(photoId)
        }
    }

    fun clearHistory() {
        viewModelScope.launch {
            searchHistoryUseCase.clearHistory()
        }
    }

    fun onQueryChange(query: String) {
        queryFlow.value = query
        _uiState.value = _uiState.value.copy(query = query)
    }

    @OptIn(FlowPreview::class)
    private fun observeQuery() {
        viewModelScope.launch {
            queryFlow
                .debounce(500)
                .map { it.trim() }
                .distinctUntilChanged()
                .collectLatest { query ->

                    if (query.isBlank()) {
                        _uiState.value = _uiState.value.copy(
                            query = "",
                            photos = emptyList(),
                            isLoading = false,
                            isLoadingMore = false,
                            page = 1,
                            hasNextPage = true,
                            error = null,
                        )
                        return@collectLatest
                    }

                    loadFirstPage(query)
                }
        }
    }

    private suspend fun loadFirstPage(query: String) {

        try {
            val result = searchPhotosUseCase.invoke(
                query = query,
                page = 1,
                perPage = 15
            )

            _uiState.value = uiState.value.copy(
                photos = result,
                page = 1,
                hasNextPage = result.isNotEmpty(),
                isLoading = false
            )

        } catch (e: Exception) {
            _uiState.value = SearchPhotosUiState(
                query = query,
                error = e.message,
                isLoading = false
            )
        }
    }

    fun loadNextPage() {

        val state = _uiState.value

        if (state.isLoadingMore || !state.hasNextPage) return
        if (state.query.isBlank()) return

        viewModelScope.launch {

            _uiState.value = state.copy(isLoadingMore = true)

            try {
                val nextPage = state.page + 1

                val result = searchPhotosUseCase.invoke(
                    query = state.query,
                    page = nextPage,
                    perPage = 15
                )

                _uiState.value = state.copy(
                    photos = state.photos + result,
                    page = nextPage,
                    hasNextPage = result.isNotEmpty(),
                    isLoadingMore = false
                )

            } catch (e: Exception) {
                _uiState.value = state.copy(
                    isLoadingMore = false,
                    error = e.message
                )
            }
        }
    }
}