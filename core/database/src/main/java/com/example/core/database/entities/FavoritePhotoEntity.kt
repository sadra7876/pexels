package com.example.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_photos")
data class FavoritePhotoEntity(
    @PrimaryKey val photoId: Long,
    val likedAt: Long = System.currentTimeMillis()
)