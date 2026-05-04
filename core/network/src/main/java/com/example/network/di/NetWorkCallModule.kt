package com.example.network.di

import com.example.network.errorhandling.handler.SafeApiCaller
import com.example.network.errorhandling.handler.SafeApiCallerImpl
import com.example.network.networkCalls.photo.PhotosRemoteDataSource
import com.example.network.networkCalls.photo.PhotosRemoteDataSourceImpl
import com.example.network.networkCalls.video.VideosRemoteDataSource
import com.example.network.networkCalls.video.VideosRemoteDataSourceImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkCallModule = module {

    singleOf(::SafeApiCallerImpl) { bind<SafeApiCaller>() }

    singleOf(::PhotosRemoteDataSourceImpl) { bind<PhotosRemoteDataSource>() }

    singleOf(::VideosRemoteDataSourceImpl) { bind<VideosRemoteDataSource>() }
}