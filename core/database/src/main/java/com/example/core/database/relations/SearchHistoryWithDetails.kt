package com.example.core.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.core.database.entities.PhotoEntity
import com.example.core.database.entities.SearchHistoryEntity

data class SearchHistoryWithDetails(
    @Embedded val history: SearchHistoryEntity,

    @Relation(
        parentColumn = "photoId",
        entityColumn = "id"
    )
    val photo: PhotoEntity
)