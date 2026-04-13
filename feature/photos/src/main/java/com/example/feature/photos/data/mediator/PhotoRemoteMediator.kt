package com.example.feature.photos.data.mediator

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.core.database.dao.PhotoDao
import com.example.core.database.dao.RemoteKeysDao
import com.example.core.database.dao.TransactionRunnerDao
import com.example.core.database.entities.RemoteKeys
import com.example.core.database.relations.PhotoWithFavorite
import com.example.core.mapper.toPhotoEntity
import com.example.network.networkCalls.photo.PhotosRemoteDataSource

@OptIn(ExperimentalPagingApi::class)
class PhotoRemoteMediator(
    private val photosRemoteDataSource: PhotosRemoteDataSource,
    private val remoteKeysDao: RemoteKeysDao,
    private val photoDao: PhotoDao,
    private val transactionRunner: TransactionRunnerDao,
) : RemoteMediator<Int, PhotoWithFavorite>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PhotoWithFavorite>
    ): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = state.anchorPosition?.let { position ->
                    state.closestItemToPosition(position)?.photo?.id?.let { id ->
                        remoteKeysDao.remoteKeysPhotoId(id)
                    }
                }
                remoteKeys?.nextKey?.minus(1) ?: 1
            }

            LoadType.PREPEND -> return MediatorResult.Success(
                endOfPaginationReached = true
            )

            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull()
                val remoteKeys = lastItem?.photo?.id?.let {
                    remoteKeysDao.remoteKeysPhotoId(it)
                }
                remoteKeys?.nextKey ?: return MediatorResult.Success(true)
            }
        }

        Log.d("Mediator", "LoadType: $loadType page: $page")

        try {
            val response = photosRemoteDataSource.getCuratedPhotos(page, state.config.pageSize).photos

            val endOfPagination = response.isEmpty()

            transactionRunner {

                val keys = response.map {
                    RemoteKeys(
                        photoId = it.id,
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = if (endOfPagination) null else page + 1
                    )
                }

                remoteKeysDao.insertAll(keys)

                photoDao.insertAll(
                    response.map {
                        it.toPhotoEntity(page)
                    }
                )
            }

            return MediatorResult.Success(endOfPagination)

        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }
}