package com.example.network.errorhandling.handler

import com.example.network.errorhandling.ApiExceptions
import okhttp3.Headers
import retrofit2.Response

internal class NetworkCallErrorHandlerImpl: NetworkCallErrorHandler {
    override suspend fun <T> handleNetworkCall(executor: suspend () -> Response<T>): T {
        return try {
            val response = executor()

            val body = response.body()
                ?: throw
                if (response.code() == 404) {
                ApiExceptions.NotFoundException
                } else {
                ApiExceptions.ConnectionError
                }
            body
        }catch (
            e: Exception
        ) {
            throw e
        }

    }

}