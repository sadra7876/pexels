package com.example.feature.favoritephotos.di

import com.example.feature.favoritephotos.data.repository.FavoritePhotosRepositoryImpl
import com.example.feature.favoritephotos.domain.repository.FavoritePhotosRepository
import com.example.feature.favoritephotos.domain.usecases.GetFavoritePhotosUseCase
import com.example.feature.favoritephotos.domain.usecases.GetFavoritePhotosUseCaseImpl
import com.example.feature.favoritephotos.ui.FavoritePhotosViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val favoritePhotosFeatureModule = module {

    singleOf(::GetFavoritePhotosUseCaseImpl) { bind<GetFavoritePhotosUseCase>() }

    singleOf(::FavoritePhotosRepositoryImpl) { bind<FavoritePhotosRepository>() }

    viewModelOf(::FavoritePhotosViewModel)
}