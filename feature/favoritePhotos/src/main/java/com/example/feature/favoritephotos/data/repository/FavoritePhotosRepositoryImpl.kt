package com.example.feature.favoritephotos.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.core.database.dao.FavoriteDao
import com.example.feature.favoritephotos.data.mapper.toFavoritePhotoDN
import com.example.feature.favoritephotos.domain.models.FavoritePhotoDN
import com.example.feature.favoritephotos.domain.repository.FavoritePhotosRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class FavoritePhotosRepositoryImpl(
    private val favoriteDao: FavoriteDao
) : FavoritePhotosRepository {
    override fun getFavoritePhotos(): Flow<PagingData<FavoritePhotoDN>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { favoriteDao.getFavorites() }
        ).flow
            .map { pagingData ->
                pagingData.map { it.toFavoritePhotoDN() }
            }
    }
}