package com.example.searchphotos.di

import com.example.searchphotos.data.repository.SearchPhotoRepositoryImpl
import com.example.searchphotos.domain.repository.SearchPhotoRepository
import com.example.searchphotos.domain.usecases.api.SearchHistoryUseCase
import com.example.searchphotos.domain.usecases.api.SearchPhotosUseCase
import com.example.searchphotos.domain.usecases.impl.SearchHistoryUseCaseImpl
import com.example.searchphotos.domain.usecases.impl.SearchPhotosUseCaseImpl
import com.example.searchphotos.ui.screens.SearchPhotosViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val searchPhotosFeatureModule = module {

    singleOf(::SearchPhotosUseCaseImpl) { bind<SearchPhotosUseCase>() }

    singleOf(::SearchHistoryUseCaseImpl) { bind<SearchHistoryUseCase>() }

    singleOf(::SearchPhotoRepositoryImpl) { bind<SearchPhotoRepository>() }

    viewModelOf(::SearchPhotosViewModel)
}