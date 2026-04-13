package com.example.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.core.database.entities.FavoritePhotoEntity
import com.example.core.database.relations.FavoritePhotoWithDetails

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorite(fav: FavoritePhotoEntity)

    @Transaction
    @Query("""
    SELECT * FROM favorite_photos
    ORDER BY likedAt DESC
""")
    fun getFavorites(): PagingSource<Int, FavoritePhotoWithDetails>

    @Query("DELETE FROM favorite_photos WHERE photoId = :id")
    suspend fun removeFromFavorite(id: Long)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_photos WHERE photoId = :id)")
    suspend fun isFavorite(id: Long): Boolean
}