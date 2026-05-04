package com.example.core.database.di

import android.content.Context
import androidx.room.Room
import com.example.core.database.PexelsDataBase
import com.example.core.database.migrations.MIGRATION_1_2
import com.example.core.database.migrations.MIGRATION_2_3
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            get<Context>().applicationContext,
            PexelsDataBase::class.java,
            "app_database"
        )
            .addMigrations(
                MIGRATION_1_2,
                MIGRATION_2_3
            )
            .build()
    }

    single { get<PexelsDataBase>().favoriteDao() }
    single { get<PexelsDataBase>().photoDao() }
    single { get<PexelsDataBase>().remoteKeysDao() }
    single { get<PexelsDataBase>().transactionRunnerDao() }
    single { get<PexelsDataBase>().searchHistoryDao() }
}