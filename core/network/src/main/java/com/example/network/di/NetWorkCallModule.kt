package com.example.network.di

import com.example.network.errorhandling.handler.SafeApiCaller
import com.example.network.errorhandling.handler.SafeApiCallerImpl
import com.example.network.networkCalls.photo.HiltTestImpl
import com.example.network.networkCalls.photo.PhotosRemoteDataSource
import com.example.network.networkCalls.photo.PhotosRemoteDataSourceImpl
import com.example.network.networkCalls.video.VideosRemoteDataSource
import com.example.network.networkCalls.video.VideosRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NetWorkCallModule {

    @Binds
    @Singleton
    abstract fun provideSafeApiCaller (
        safeApiCallerImpl: SafeApiCallerImpl
    ): SafeApiCaller

    @Binds
    abstract fun providePhotosRemoteDataSource(
        photosRemoteDataSourceImpl: HiltTestImpl
    ): PhotosRemoteDataSource

    @Binds
    abstract fun provideVideosRemoteDataSource(
        videosRemoteDataSourceImpl: VideosRemoteDataSourceImpl
    ): VideosRemoteDataSource
}