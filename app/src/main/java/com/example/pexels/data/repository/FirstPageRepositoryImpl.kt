package com.example.pexels.data.repository

import com.example.network.errorhandling.ApiExceptions
import com.example.network.networkCalls.FirstPageCall
import com.example.pexels.data.mapper.toPhotoDN
import com.example.pexels.domain.models.PhotoDN
import com.example.pexels.domain.repository.FirstPageRepository

class FirstPageRepositoryImpl(
    private val firstPageCall: FirstPageCall
): FirstPageRepository {
    override suspend fun getPhotos(page: Int, perPage: Int): List<PhotoDN> {
        return firstPageCall.getCuratedPhotos(page, perPage).photos.map { it.toPhotoDN() }
    }
}