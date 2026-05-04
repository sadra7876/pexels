package com.example.feature.photodetail.di

import com.example.feature.photodetail.data.repository.PhotoDetailRepositoryImpl
import com.example.feature.photodetail.domain.repository.PhotoDetailRepository
import com.example.feature.photodetail.domain.usecases.api.FavoritePhotoUseCase
import com.example.feature.photodetail.domain.usecases.api.GetPhotoUseCase
import com.example.feature.photodetail.domain.usecases.impl.FavoritePhotoUseCaseImpl
import com.example.feature.photodetail.domain.usecases.impl.GetPhotoUseCaseImpl
import com.example.feature.photodetail.ui.screens.PhotoDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val photoDetailFeatureModule = module {

    singleOf(::FavoritePhotoUseCaseImpl) { bind<FavoritePhotoUseCase>() }

    singleOf(::GetPhotoUseCaseImpl) { bind<GetPhotoUseCase>() }

    singleOf(::PhotoDetailRepositoryImpl) { bind<PhotoDetailRepository>() }

    viewModelOf(::PhotoDetailViewModel)

}