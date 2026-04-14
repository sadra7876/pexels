package com.example.core.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE search_history (
                photoId INTEGER NOT NULL PRIMARY KEY,
                timestamp INTEGER NOT NULL
            )
        """.trimIndent())
    }
}