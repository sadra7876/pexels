package com.example.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.core.database.dao.FavoriteDao
import com.example.core.database.dao.PhotoDao
import com.example.core.database.dao.RemoteKeysDao
import com.example.core.database.dao.TransactionRunnerDao
import com.example.core.database.entities.FavoritePhotoEntity
import com.example.core.database.entities.PhotoEntity
import com.example.core.database.entities.RemoteKeys
import com.example.core.database.utils.Converters

@Database(
    entities = [
        PhotoEntity::class,
        RemoteKeys::class,
        FavoritePhotoEntity::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class PexelsDataBase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
    abstract fun remoteKeysDao(): RemoteKeysDao
    abstract fun favoriteDao(): FavoriteDao
    abstract fun transactionRunnerDao(): TransactionRunnerDao
}
