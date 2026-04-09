package com.example.network.errorhandling.handler

import android.util.Log
import com.example.network.errorhandling.ApiExceptions
import retrofit2.Response
import java.net.SocketTimeoutException

internal class SafeApiCallerImpl: SafeApiCaller {
    override suspend fun <T> executeSafely(executor: suspend () -> Response<T>): T {
        return try {
            val response = executor()
             when (response.code()) {
                 404 -> throw ApiExceptions.NotFoundException
                 401 -> throw ApiExceptions.UnauthorizedException
                 429 -> throw ApiExceptions.RateLimitException
                 500 -> throw ApiExceptions.ServerErrorException
            }
            response.body()?: throw ApiExceptions.UnKnownException
        }catch (
            e: ApiExceptions
        ) {
            throw e
        } catch (
            e: SocketTimeoutException
        ) {
            throw ApiExceptions.TimeoutException
        } catch (
            e: Exception
        ) {
            Log.d("Photos", "executeSafely: $e")
            throw ApiExceptions.UnKnownException
        }

    }

}