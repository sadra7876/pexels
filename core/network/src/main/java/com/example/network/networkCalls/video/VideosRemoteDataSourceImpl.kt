package com.example.network.networkCalls.video


import com.example.network.dto.response.video.PopularVideoResponseDto
import com.example.network.endpoints.VideosEndPoint
import com.example.network.errorhandling.ApiExceptions
import com.example.network.errorhandling.handler.SafeApiCaller

internal class VideosRemoteDataSourceImpl(
    private val videosEndpoint: VideosEndPoint,
    private val safeApiCaller: SafeApiCaller
): VideosRemoteDataSource {
    override suspend fun getPopularVideos(
        minWidth: Int?,
        minHeight: Int?,
        minDuration: Int?,
        maxDuration: Int?,
        page: Int,
        perPage: Int
    ): PopularVideoResponseDto {
        return try {
            safeApiCaller.executeSafely {
                videosEndpoint.getPopularVideos(
                    minWidth = minWidth,
                    minHeight = minHeight,
                    minDuration = minDuration,
                    maxDuration = maxDuration,
                    page = page,
                    perPage = perPage)
            }
        }catch (e:ApiExceptions){
            throw e
        }
    }
}