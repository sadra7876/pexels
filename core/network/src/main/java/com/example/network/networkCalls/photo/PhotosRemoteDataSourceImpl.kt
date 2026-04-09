package com.example.network.networkCalls.photo

import com.example.network.dto.response.photo.CuratedResponseDto
import com.example.network.dto.response.photo.PhotoResponseDto
import com.example.network.endpoints.PhotosEndpoint
import com.example.network.errorhandling.ApiExceptions
import com.example.network.errorhandling.handler.SafeApiCaller

internal class PhotosRemoteDataSourceImpl(
    private val photosEndpoint: PhotosEndpoint,
    private val safeApiCaller: SafeApiCaller
): PhotosRemoteDataSource {
    override suspend fun getCuratedPhotos(page: Int, perPage: Int): CuratedResponseDto {
        return try {
            safeApiCaller.executeSafely {
                photosEndpoint.getCuratedPhotos(page, perPage)
            }
        }catch (e:ApiExceptions){
            throw e
        }
    }

    override suspend fun getPhoto(id: Long): PhotoResponseDto {
        return try {
            safeApiCaller.executeSafely {
                photosEndpoint.getPhoto(id)
            }
        }catch (e:ApiExceptions){
            throw e
        }
    }
}