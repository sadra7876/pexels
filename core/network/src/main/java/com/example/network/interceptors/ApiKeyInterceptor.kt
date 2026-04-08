package com.example.network.interceptors

import com.example.network.constants.HeaderConstants
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request()
            .newBuilder()
            .addHeader(
                HeaderConstants.AUTHORIZATION,
                HeaderConstants.AUTHORIZATION_TYPE + getApiKey()
            )
            .build()

        return chain.proceed(newRequest)
    }

    private fun getApiKey() : String{
        return "cw91mgE0XmELV4NV6bmsq7sIqp2irZdCLTRtJvHXaaxXau1bZE1YcBkw"
    }
}