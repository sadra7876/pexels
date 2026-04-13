package com.example.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.core.database.entities.PhotoEntity
import com.example.core.database.relations.PhotoWithFavorite


@Dao
interface PhotoDao {

    @Transaction
    @Query("SELECT * FROM photos ORDER BY page, id")
    fun photoWithFavoritePagingSource(): PagingSource<Int, PhotoWithFavorite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(photos: List<PhotoEntity>)

    @Query("DELETE FROM photos")
    suspend fun clearAll()
}