package com.example.network.di

import com.example.network.errorhandling.handler.NetworkCallErrorHandler
import com.example.network.errorhandling.handler.NetworkCallErrorHandlerImpl
import com.example.network.networkCalls.FirstPageCall
import com.example.network.networkCalls.FirstPageCallImpl

object NetWorkCallProvider {
    private val networkCallErrorHandler: NetworkCallErrorHandler = NetworkCallErrorHandlerImpl()

    val firstPageCall: FirstPageCall = FirstPageCallImpl(
        firstPageEndpoint = EndPointProvider.firstPageEndpoint,
        networkCallErrorHandler = networkCallErrorHandler
    )
}