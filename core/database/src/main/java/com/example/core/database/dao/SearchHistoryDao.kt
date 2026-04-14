package com.example.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.database.entities.SearchHistoryEntity
import com.example.core.database.relations.SearchHistoryWithDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchHistoryDao {

    @Query("SELECT * FROM search_history ORDER BY timestamp DESC LIMIT 20")
    fun getHistory(): Flow<List<SearchHistoryWithDetails>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: SearchHistoryEntity)

    @Query("DELETE FROM search_history WHERE photoId = :id")
    suspend fun deleteByPhotoIdId(id: Long)

    @Query("DELETE FROM search_history")
    suspend fun clearAll()

    @Query("""
        DELETE FROM search_history 
        WHERE photoId NOT IN (
            SELECT photoId FROM search_history 
            ORDER BY timestamp DESC LIMIT 20
        )
    """)
    suspend fun keepOnlyLatest20()
}