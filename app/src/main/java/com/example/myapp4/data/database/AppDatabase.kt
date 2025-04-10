package com.example.myapp4.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapp4.data.dao.NotesDao
import com.example.myapp4.data.entities.Note

@Database(
    entities = [Note::class], version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}