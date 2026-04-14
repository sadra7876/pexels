package com.example.feature.favoritephotos.domain.repository

import androidx.paging.PagingData
import com.example.feature.favoritephotos.domain.models.FavoritePhotoDN
import kotlinx.coroutines.flow.Flow

interface FavoritePhotosRepository {
    fun getFavoritePhotos(): Flow<PagingData<FavoritePhotoDN>>

}