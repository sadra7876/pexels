package com.example.network.endpoints

import com.example.network.dto.response.photo.CuratedResponseDto
import com.example.network.dto.response.photo.PhotoResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface PhotosEndpoint {

    @GET("curated")
    suspend fun getCuratedPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): Response<CuratedResponseDto>

    @GET("photos/{id}")
    suspend fun getPhoto(
        @Path("id") id: Long
    ): Response<PhotoResponseDto>
}