package com.example.myapp4.data.localDataSources.noteDataSource

import com.example.myapp4.data.dao.NotesDao
import com.example.myapp4.data.entities.Note
import kotlinx.coroutines.flow.Flow

class NoteLocalDataSourceImpl(
    private val notesDao: NotesDao
) : NoteLocalDataSource {

    override suspend fun addNote(note: Note) {
        notesDao.addNote(note)
    }

    override suspend fun updateNote(note: Note) {
        notesDao.updateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        notesDao.deleteNote(note.id)
    }

    override fun getNotes(): Flow<List<Note>> {
        return notesDao.getNotes()
    }
}