package com.example.network.endpoints

import com.example.network.dto.response.video.PopularVideoResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface VideosEndPoint {

    @GET("videos/popular")
    suspend fun getPopularVideos(
        @Query("min_width") minWidth: Int?,
        @Query("min_height") minHeight: Int?,
        @Query("min_duration") minDuration: Int?,
        @Query("max_duration") maxDuration: Int?,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): Response<PopularVideoResponseDto>

}