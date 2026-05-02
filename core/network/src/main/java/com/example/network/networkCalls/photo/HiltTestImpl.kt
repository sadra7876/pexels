package com.example.network.networkCalls.photo

import com.example.network.dto.response.photo.CuratedResponseDto
import com.example.network.dto.response.photo.PhotoResponseDto
import com.example.network.dto.response.photo.PhotoSrcResponseDto
import com.example.network.dto.response.photo.SearchResponseDto
import javax.inject.Inject
import kotlin.Int
import kotlin.Long

internal class HiltTestImpl @Inject constructor(): PhotosRemoteDataSource {
    override suspend fun getCuratedPhotos(page: Int, perPage: Int): CuratedResponseDto =
        CuratedResponseDto(listOf(
            PhotoResponseDto(
                id = 36634665L,
                avgColor = "#7C97C2",
                width = 5400,
                height = 3600,
                src = PhotoSrcResponseDto(
                    large = "https://images.pexels.com/photos/36634665/pexels-photo-36634665.jpeg?auto=compress\u0026cs=tinysrgb\u0026h=650\u0026w=940",
                    original = "https://images.pexels.com/photos/36634665/pexels-photo-36634665.jpeg",
                    medium = "https://images.pexels.com/photos/36634665/pexels-photo-36634665.jpeg?auto=compress\u0026cs=tinysrgb\u0026h=350",
                    small = "https://images.pexels.com/photos/36634665/pexels-photo-36634665.jpeg?auto=compress\u0026cs=tinysrgb\u0026h=130",
                    tiny = "https://images.pexels.com/photos/36634665/pexels-photo-36634665.jpeg?auto=compress\u0026cs=tinysrgb\u0026dpr=1\u0026fit=crop\u0026h=200\u0026w=280",
                ),
                photographer = "Man Fong Wong",
                alt = "Salt farmers working on a salt field under a clear blue sky in Vietnam.",
            )
        ))

    override suspend fun getPhoto(id: Long): PhotoResponseDto =
        PhotoResponseDto(
            id = 36634665L,
            avgColor = "#7C97C2",
            width = 5400,
            height = 3600,
            src = PhotoSrcResponseDto(
                large = "https://images.pexels.com/photos/36634665/pexels-photo-36634665.jpeg?auto=compress\u0026cs=tinysrgb\u0026h=650\u0026w=940",
                original = "https://images.pexels.com/photos/36634665/pexels-photo-36634665.jpeg",
                medium = "https://images.pexels.com/photos/36634665/pexels-photo-36634665.jpeg?auto=compress\u0026cs=tinysrgb\u0026h=350",
                small = "https://images.pexels.com/photos/36634665/pexels-photo-36634665.jpeg?auto=compress\u0026cs=tinysrgb\u0026h=130",
                tiny = "https://images.pexels.com/photos/36634665/pexels-photo-36634665.jpeg?auto=compress\u0026cs=tinysrgb\u0026dpr=1\u0026fit=crop\u0026h=200\u0026w=280",
            ),
            photographer = "Man Fong Wong",
            alt = "Salt farmers working on a salt field under a clear blue sky in Vietnam.",
        )


    override suspend fun searchPhotos(query: String, page: Int, perPage: Int): SearchResponseDto =
        SearchResponseDto(listOf(
            PhotoResponseDto(
                id = 36634665L,
                avgColor = "#7C97C2",
                width = 5400,
                height = 3600,
                src = PhotoSrcResponseDto(
                    large = "https://images.pexels.com/photos/36634665/pexels-photo-36634665.jpeg?auto=compress\u0026cs=tinysrgb\u0026h=650\u0026w=940",
                    original = "https://images.pexels.com/photos/36634665/pexels-photo-36634665.jpeg",
                    medium = "https://images.pexels.com/photos/36634665/pexels-photo-36634665.jpeg?auto=compress\u0026cs=tinysrgb\u0026h=350",
                    small = "https://images.pexels.com/photos/36634665/pexels-photo-36634665.jpeg?auto=compress\u0026cs=tinysrgb\u0026h=130",
                    tiny = "https://images.pexels.com/photos/36634665/pexels-photo-36634665.jpeg?auto=compress\u0026cs=tinysrgb\u0026dpr=1\u0026fit=crop\u0026h=200\u0026w=280",
                ),
                photographer = "Man Fong Wong",
                alt = "Salt farmers working on a salt field under a clear blue sky in Vietnam.",
            )
        ))
}