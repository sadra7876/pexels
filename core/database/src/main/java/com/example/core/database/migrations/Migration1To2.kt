package com.example.core.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {

        db.execSQL("""
            ALTER TABLE favorite_photos 
            ADD COLUMN likedAt INTEGER NOT NULL DEFAULT 0
        """.trimIndent())
    }
}