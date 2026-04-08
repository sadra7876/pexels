package com.example.pexels.di

import com.example.network.di.NetWorkCallProvider
import com.example.pexels.data.repository.FirstPageRepositoryImpl
import com.example.pexels.domain.repository.FirstPageRepository
import com.example.pexels.domain.usecases.GetPhotosUseCase
import com.example.pexels.domain.usecases.GetPhotosUseCaseImpl
import com.example.pexels.ui.screens.PhotosViewModel

object FirstPageProvider {
    private val firstPageRepository:FirstPageRepository = FirstPageRepositoryImpl(NetWorkCallProvider.firstPageCall)

    private val getPhotosUseCase : GetPhotosUseCase = GetPhotosUseCaseImpl(firstPageRepository)

    val photosViewModel = PhotosViewModel(getPhotosUseCase)

}