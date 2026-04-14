package com.example.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_history")
data class SearchHistoryEntity(
    @PrimaryKey val photoId: Long,
    val timestamp: Long = System.currentTimeMillis()
)