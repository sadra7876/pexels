package com.example.network.di

import com.example.network.endpoints.PhotosEndpoint
import com.example.network.endpoints.VideosEndPoint
import org.koin.dsl.module
import retrofit2.Retrofit

val endPointModule = module {

    single<PhotosEndpoint> {
        get<Retrofit>().create(PhotosEndpoint::class.java)
    }

    single<VideosEndPoint> {
        get<Retrofit>().create(VideosEndPoint::class.java)
    }
}