package com.example.searchphotos.data.repository

import com.example.network.networkCalls.photo.PhotosRemoteDataSource
import com.example.searchphotos.data.mapper.toSearchPhotoDN
import com.example.searchphotos.domain.models.SearchPhotoDN
import com.example.searchphotos.domain.repository.SearchPhotoRepository

class SearchPhotoRepositoryImpl(
    private val photosRemoteDataSource: PhotosRemoteDataSource
) : SearchPhotoRepository {
    override suspend fun searchPhotos(query: String, page: Int, perPage: Int): List<SearchPhotoDN> {
        return photosRemoteDataSource.searchPhotos(query, page, perPage).photos.map { it.toSearchPhotoDN() }
    }
}