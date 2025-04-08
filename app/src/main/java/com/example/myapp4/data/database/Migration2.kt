package com.example.myapp4.data.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE Note_new (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                title TEXT NOT NULL,
                text TEXT NOT NULL,
                timeStamp INTEGER NOT NULL DEFAULT 0
            )
        """.trimIndent())

        db.execSQL("""
            INSERT INTO Note_new (id, title, text, timeStamp)
            SELECT id, title, text, dateAdded FROM Note
        """.trimIndent())

        db.execSQL("DROP TABLE Note")

        db.execSQL("ALTER TABLE Note_new RENAME TO Note")
    }
}