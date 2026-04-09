package com.example.network.networkCalls.video

import com.example.network.dto.response.video.PopularVideoResponseDto


interface VideosRemoteDataSource {
    suspend fun getPopularVideos(
        minWidth: Int?,
        minHeight: Int?,
        minDuration: Int?,
        maxDuration: Int?,
        page: Int,
        perPage: Int,
    ): PopularVideoResponseDto

}