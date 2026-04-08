package com.example.network.networkCalls

import com.example.network.dto.response.Curated.CuratedResponseDto
import com.example.network.endpoints.FirstPageEndpoint
import com.example.network.errorhandling.ApiExceptions
import com.example.network.errorhandling.handler.NetworkCallErrorHandler

internal class FirstPageCallImpl(
    private val firstPageEndpoint: FirstPageEndpoint,
    private val networkCallErrorHandler: NetworkCallErrorHandler
): FirstPageCall {
    override suspend fun getCuratedPhotos(page: Int, perPage: Int): CuratedResponseDto {
        return try {
//            refactore the name
            networkCallErrorHandler.handleNetworkCall {
                firstPageEndpoint.getCuratedPhotos(page, perPage)
            }
        }catch (e:ApiExceptions){
            throw e
        }
    }
}