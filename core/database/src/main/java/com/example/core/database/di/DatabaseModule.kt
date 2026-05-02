package com.example.core.database.di

import com.example.core.database.PexelsDataBase
import android.content.Context
import androidx.room.Room
import com.example.core.database.migrations.MIGRATION_1_2
import com.example.core.database.migrations.MIGRATION_2_3
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext
        context: Context
    ): PexelsDataBase {
        return Room.databaseBuilder(
                context.applicationContext,
                PexelsDataBase::class.java,
                "app_database"
            ).addMigrations(
                MIGRATION_1_2,
                MIGRATION_2_3
            ).build()
        }

    @Provides
    @Singleton
    fun provideFavoriteDao(
        database: PexelsDataBase
    ) = database.favoriteDao()

    @Provides
    @Singleton
    fun providePhotoDao(
        database: PexelsDataBase
    ) = database.photoDao()

    @Provides
    @Singleton
    fun provideRemoteKeysDao(
        database: PexelsDataBase
    ) = database.remoteKeysDao()

    @Provides
    @Singleton
    fun provideTransactionRunnerDao(
        database: PexelsDataBase
    ) = database.transactionRunnerDao()

    @Provides
    @Singleton
    fun provideSearchHistoryDao(
        database: PexelsDataBase
    ) = database.searchHistoryDao()
}