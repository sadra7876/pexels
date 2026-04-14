package com.example.feature.photos.domain.usecases.api

import kotlinx.coroutines.flow.Flow

interface DarkModeUseCase {
    suspend fun updateDarkMode(toDark: Boolean)

    fun getDarkMode(): Flow<Boolean>
}