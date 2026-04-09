package com.example.network.errorhandling.handler

import retrofit2.Response

interface SafeApiCaller {
    suspend fun <T> executeSafely(
        executor: suspend () -> Response<T>
    ): T
}