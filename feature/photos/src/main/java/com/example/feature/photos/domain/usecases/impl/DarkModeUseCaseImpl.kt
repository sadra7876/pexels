package com.example.feature.photos.domain.usecases.impl

import com.example.feature.photos.domain.repository.PhotosRepository
import com.example.feature.photos.domain.usecases.api.DarkModeUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class DarkModeUseCaseImpl @Inject constructor(
    private val repository: PhotosRepository
) : DarkModeUseCase {
    override suspend fun updateDarkMode(toDark: Boolean) {
        repository.updateDarkMode(toDark)
    }

    override fun getDarkMode(): Flow<Boolean> {
        return repository.getDarkMode()
    }
}