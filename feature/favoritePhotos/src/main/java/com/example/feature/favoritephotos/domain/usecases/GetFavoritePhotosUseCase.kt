package com.example.feature.favoritephotos.domain.usecases

import androidx.paging.PagingData
import com.example.feature.favoritephotos.domain.models.FavoritePhotoDN
import kotlinx.coroutines.flow.Flow

interface GetFavoritePhotosUseCase {
    operator fun invoke(): Flow<PagingData<FavoritePhotoDN>>

}