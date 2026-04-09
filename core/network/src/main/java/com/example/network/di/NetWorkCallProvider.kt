package com.example.network.di

import com.example.network.errorhandling.handler.SafeApiCaller
import com.example.network.errorhandling.handler.SafeApiCallerImpl
import com.example.network.networkCalls.photo.PhotosRemoteDataSource
import com.example.network.networkCalls.photo.PhotosRemoteDataSourceImpl
import com.example.network.networkCalls.video.VideosRemoteDataSource
import com.example.network.networkCalls.video.VideosRemoteDataSourceImpl

object NetWorkCallProvider {
    private val safeApiCaller: SafeApiCaller = SafeApiCallerImpl()

    val photosRemoteDataSource: PhotosRemoteDataSource = PhotosRemoteDataSourceImpl(
        photosEndpoint = EndPointProvider.photosEndpoint,
        safeApiCaller = safeApiCaller
    )

    val videosRemoteDataSource: VideosRemoteDataSource = VideosRemoteDataSourceImpl(
        videosEndpoint = EndPointProvider.videosEndpoint,
        safeApiCaller = safeApiCaller
    )
}