package com.example.network.di

import com.example.network.endpoints.FirstPageEndpoint

object EndPointProvider {
    internal val firstPageEndpoint : FirstPageEndpoint = NetworkProvider.retrofit.create(FirstPageEndpoint::class.java)
}