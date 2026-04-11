package com.example.searchphotos.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchphotos.domain.usecases.SearchPhotosUseCase
import com.example.searchphotos.ui.contracts.SearchPhotosUiState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


class SearchPhotosViewModel(
    private val searchPhotosUseCase : SearchPhotosUseCase
) : ViewModel() {

    private val queryFlow = MutableStateFlow("")

    private val _uiState = MutableStateFlow(SearchPhotosUiState())
    val uiState = _uiState.asStateFlow()

    init {
        observeQuery()
    }

    fun onQueryChange(query: String) {
        queryFlow.value = query
        _uiState.value = SearchPhotosUiState(
            query = query,
        )
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
                        _uiState.value = SearchPhotosUiState()
                        return@collectLatest
                    }

                    loadFirstPage(query)
                }
        }
    }

    private suspend fun loadFirstPage(query: String) {

        _uiState.value = uiState.value.copy(isLoadingMore = true)

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