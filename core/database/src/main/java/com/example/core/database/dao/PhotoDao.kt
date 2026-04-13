package com.example.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.core.database.entities.PhotoEntity
import com.example.core.database.relations.PhotoWithFavorite
import kotlinx.coroutines.flow.Flow


@Dao
interface PhotoDao {

    @Transaction
    @Query("SELECT * FROM photos ORDER BY page, id")
    fun photoWithFavoritePagingSource(): PagingSource<Int, PhotoWithFavorite>

    @Query("SELECT * FROM photos WHERE id = :id")
    fun getPhotoWithFavorite(id: Long): Flow<PhotoWithFavorite?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoto(fav: PhotoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(photos: List<PhotoEntity>)
}