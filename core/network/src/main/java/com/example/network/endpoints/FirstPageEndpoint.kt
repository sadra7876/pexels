package com.example.network.endpoints

import com.example.network.dto.response.Curated.CuratedResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// refactore name
internal interface FirstPageEndpoint {

    @GET("curated")
    suspend fun getCuratedPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): Response<CuratedResponseDto>
}