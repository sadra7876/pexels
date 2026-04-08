package com.example.feature.photos.data.repository

import com.example.network.networkCalls.FirstPageCall
import com.example.feature.photos.data.mapper.toPhotoDN

internal class FirstPageRepositoryImpl(
    private val firstPageCall: FirstPageCall
): com.example.feature.photos.domain.repository.FirstPageRepository {
    override suspend fun getPhotos(page: Int, perPage: Int): List<com.example.feature.photos.domain.models.PhotoDN> {
        return firstPageCall.getCuratedPhotos(page, perPage).photos.map { it.toPhotoDN() }
    }
}