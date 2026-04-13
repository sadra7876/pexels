package com.example.core.database.di

import androidx.room.withTransaction
import com.example.core.database.PexelsDataBase
import android.content.Context
import androidx.room.Room
import com.example.core.database.migrations.MIGRATION_1_2

object DatabaseProvider {
    @Volatile
    private var INSTANCE: PexelsDataBase? = null

    private fun getDatabase(context: Context): PexelsDataBase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                PexelsDataBase::class.java,
                "app_database"
            ).addMigrations(
                MIGRATION_1_2
            ).build()

            INSTANCE = instance
            instance
        }
    }
   suspend fun <R>withTransaction(
       context: Context,
       block: suspend () -> R
   ) = getDatabase(context).withTransaction(block = block)

    fun favoriteDao(context: Context) = getDatabase(context = context).favoriteDao()
    fun photoDao(context: Context) = getDatabase(context = context).photoDao()
    fun remoteKeysDao(context: Context) = getDatabase(context = context).remoteKeysDao()
    fun transactionRunnerDao(context: Context) = getDatabase(context = context).transactionRunnerDao()
}