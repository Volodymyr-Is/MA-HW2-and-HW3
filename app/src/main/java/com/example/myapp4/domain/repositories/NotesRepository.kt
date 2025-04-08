package com.example.myapp4.domain.repositories

import com.example.myapp4.data.entities.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    suspend fun addNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)

    fun getNotes(): Flow<List<Note>>
}