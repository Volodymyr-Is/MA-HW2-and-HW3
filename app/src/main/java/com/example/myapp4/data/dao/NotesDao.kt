package com.example.myapp4.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapp4.data.entities.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Insert
    fun addNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Query("DELETE FROM Note WHERE id = :noteId")
    fun deleteNote(noteId: Int)

    @Query("SELECT * FROM Note")
    fun getNotes(): Flow<List<Note>>
}