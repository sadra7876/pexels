package com.example.feature.photos.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.core.database.dao.PhotoDao
import com.example.core.database.dao.RemoteKeysDao
import com.example.core.database.dao.TransactionRunnerDao
import com.example.feature.photos.data.mapper.toPhotoListDN
import com.example.feature.photos.data.mediator.PhotoRemoteMediator
import com.example.network.networkCalls.photo.PhotosRemoteDataSource
import com.example.feature.photos.domain.models.PhotoListDN
import com.example.feature.photos.domain.models.PhotoListSrcDN
import com.example.feature.photos.domain.repository.PhotosRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class PhotosRepositoryImpl(
    private val photosRemoteDataSource: PhotosRemoteDataSource,
    private val remoteKeysDao: RemoteKeysDao,
    private val photoDao: PhotoDao,
    private val transactionRunner: TransactionRunnerDao,
    ): PhotosRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getPhotos(): Flow<PagingData<PhotoListDN>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                initialLoadSize = 30
            ),
            pagingSourceFactory = {
                photoDao.photoWithFavoritePagingSource()
            },
            remoteMediator = PhotoRemoteMediator(
                photosRemoteDataSource = photosRemoteDataSource,
                remoteKeysDao = remoteKeysDao,
                photoDao = photoDao,
                transactionRunner = transactionRunner,
            )
        ).flow
            .map { pagingData ->
                pagingData.map { entity ->
                    entity.toPhotoListDN()
                }
            }
    }
}