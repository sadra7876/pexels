package com.example.core.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.core.database.entities.FavoritePhotoEntity
import com.example.core.database.entities.PhotoEntity

data class FavoritePhotoWithDetails(
    @Embedded val favorite: FavoritePhotoEntity,

    @Relation(
        parentColumn = "photoId",
        entityColumn = "id"
    )
    val photo: PhotoEntity
)