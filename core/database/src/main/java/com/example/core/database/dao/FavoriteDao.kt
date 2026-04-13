package com.example.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.database.entities.FavoritePhotoEntity

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorite(fav: FavoritePhotoEntity)

    @Query("DELETE FROM favorite_photos WHERE photoId = :id")
    suspend fun removeFromFavorite(id: Long)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_photos WHERE photoId = :id)")
    suspend fun isFavorite(id: Long): Boolean
}