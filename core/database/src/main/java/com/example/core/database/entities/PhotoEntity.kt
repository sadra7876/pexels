package com.example.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.database.entities.dbo.PhotoSrcDbo

@Entity(tableName = "photos")
data class PhotoEntity(
    @PrimaryKey val id: Long,
    val avgColor: String,
    val width: Int,
    val height: Int,
    val src: PhotoSrcDbo,
    val photographer: String,
    val alt: String,
    val page: Int
)