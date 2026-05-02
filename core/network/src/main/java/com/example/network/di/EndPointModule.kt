package com.example.network.di

import com.example.network.endpoints.PhotosEndpoint
import com.example.network.endpoints.VideosEndPoint
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object EndPointModule {

    @Provides
    @Singleton
    fun providePhotosEndpoint(
        retrofit: Retrofit
    ) : PhotosEndpoint = retrofit.create(PhotosEndpoint::class.java)

    fun provideVideosEndpoint(
        retrofit: Retrofit
    ) : VideosEndPoint = retrofit.create(VideosEndPoint::class.java)

}