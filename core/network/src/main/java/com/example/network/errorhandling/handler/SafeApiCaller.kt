package com.example.network.errorhandling.handler

import retrofit2.Response

interface SafeApiCaller {
    suspend fun <T : Any> executeSafely(
        executor: suspend () -> Response<T>
    ): T
}