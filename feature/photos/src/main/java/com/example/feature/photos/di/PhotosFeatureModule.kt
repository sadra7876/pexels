package com.example.feature.photos.di

import com.example.feature.photos.data.repository.PhotosRepositoryImpl
import com.example.feature.photos.domain.repository.PhotosRepository
import com.example.feature.photos.domain.usecases.api.DarkModeUseCase
import com.example.feature.photos.domain.usecases.api.GetPhotosUseCase
import com.example.feature.photos.domain.usecases.impl.DarkModeUseCaseImpl
import com.example.feature.photos.domain.usecases.impl.GetPhotosUseCaseImpl
import com.example.feature.photos.ui.screens.PhotosViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val photosFeatureModule = module{

    singleOf(::DarkModeUseCaseImpl) { bind<DarkModeUseCase>() }

    singleOf(::GetPhotosUseCaseImpl) { bind<GetPhotosUseCase>() }

    singleOf(::PhotosRepositoryImpl) { bind<PhotosRepository>() }

    viewModelOf(::PhotosViewModel)

}
