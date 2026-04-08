package com.example.network.errorhandling.handler

import retrofit2.Response

interface NetworkCallErrorHandler {
    suspend fun <T> handleNetworkCall(
        executor: suspend () -> Response<T>
    ): T
}