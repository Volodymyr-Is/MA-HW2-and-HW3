package com.example.myapp4.data.database

import android.content.Context
import androidx.room.Room
import com.example.myapp4.data.dao.NotesDao

private const val DB_NAME = "list-db"

class DatabaseHelper private constructor(context: Context) {
    private val database: AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        DB_NAME
    )
        .addMigrations(MIGRATION_1_2)
        .fallbackToDestructiveMigration()
        .build()

    fun getNotesDao(): NotesDao {
        return database.notesDao()
    }

    companion object {
        @Volatile
        private var INSTANCE: DatabaseHelper? = null

        fun getInstance(context: Context): DatabaseHelper {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: DatabaseHelper(context).also { INSTANCE = it }
            }
        }
    }
}