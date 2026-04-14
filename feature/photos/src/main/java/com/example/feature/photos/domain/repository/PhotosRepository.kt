package com.example.feature.photos.domain.repository

import androidx.paging.PagingData
import com.example.core.sharedmodel.dn.PhotoDN
import kotlinx.coroutines.flow.Flow

interface PhotosRepository {
    fun getPhotos(): Flow<PagingData<PhotoDN>>

    suspend fun updateDarkMode(toDark: Boolean)

    fun getDarkMode(): Flow<Boolean>
}