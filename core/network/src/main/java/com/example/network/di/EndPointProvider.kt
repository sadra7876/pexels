package com.example.network.di

import com.example.network.endpoints.PhotosEndpoint
import com.example.network.endpoints.VideosEndPoint

object EndPointProvider {
    internal val photosEndpoint : PhotosEndpoint = NetworkProvider.retrofit.create(PhotosEndpoint::class.java)

    internal val videosEndpoint : VideosEndPoint = NetworkProvider.retrofit.create(VideosEndPoint::class.java)

}