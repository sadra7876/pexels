package com.example.core.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.core.database.entities.FavoritePhotoEntity
import com.example.core.database.entities.PhotoEntity

data class PhotoWithFavorite(
    @Embedded val photo: PhotoEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "photoId"
    )
    val favorite: FavoritePhotoEntity?
) {
    val isFavorite: Boolean
        get() = favorite != null
}