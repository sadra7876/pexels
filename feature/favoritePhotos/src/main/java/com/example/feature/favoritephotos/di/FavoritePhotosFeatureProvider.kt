package com.example.feature.favoritephotos.di

import android.content.Context
import com.example.core.database.di.DatabaseProvider
import com.example.feature.favoritephotos.data.repository.FavoritePhotosRepositoryImpl
import com.example.feature.favoritephotos.domain.usecases.GetFavoritePhotosUseCase
import com.example.feature.favoritephotos.domain.usecases.GetFavoritePhotosUseCaseImpl

object FavoritePhotosFeatureProvider {
    fun provide(context: Context): GetFavoritePhotosUseCase {

        val repository = FavoritePhotosRepositoryImpl(
            favoriteDao = DatabaseProvider.favoriteDao(context),

        )

        return GetFavoritePhotosUseCaseImpl(repository)
    }
}