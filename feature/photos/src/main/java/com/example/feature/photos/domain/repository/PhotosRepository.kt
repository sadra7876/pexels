package com.example.feature.photos.domain.repository

import androidx.paging.PagingData
import com.example.feature.photos.domain.models.PhotoListDN
import kotlinx.coroutines.flow.Flow

interface PhotosRepository {
    fun getPhotos(): Flow<PagingData<PhotoListDN>>
}