package com.example.myapp4.data.repository

import com.example.myapp4.data.entities.Note
import com.example.myapp4.data.localDataSources.noteDataSource.NoteLocalDataSource
import com.example.myapp4.domain.repositories.NotesRepository
import kotlinx.coroutines.flow.Flow

class NotesRepositoryImpl(
    private val localDataSource: NoteLocalDataSource
) : NotesRepository {

    override suspend fun addNote(note: Note) {
        localDataSource.addNote(note)
    }

    override suspend fun updateNote(note: Note) {
        localDataSource.updateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        localDataSource.deleteNote(note)
    }

    override fun getNotes(): Flow<List<Note>> {
        return localDataSource.getNotes()
    }
}