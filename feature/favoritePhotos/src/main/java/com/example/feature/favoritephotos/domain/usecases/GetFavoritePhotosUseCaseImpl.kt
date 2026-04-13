package com.example.feature.favoritephotos.domain.usecases

import androidx.paging.PagingData
import com.example.feature.favoritephotos.domain.models.FavoritePhotoListDN
import com.example.feature.favoritephotos.domain.repository.FavoritePhotosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

internal class GetFavoritePhotosUseCaseImpl (
    private val repository: FavoritePhotosRepository
) : GetFavoritePhotosUseCase {
    override fun invoke(): Flow<PagingData<FavoritePhotoListDN>> =
        repository.getFavoritePhotos().flowOn(Dispatchers.IO)


}