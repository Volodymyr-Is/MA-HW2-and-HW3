package com.example.myapp4.presentation.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp4.data.database.DatabaseHelper
import com.example.myapp4.data.entities.Note
import com.example.myapp4.data.localDataSources.noteDataSource.NoteLocalDataSourceImpl
import com.example.myapp4.data.repository.NotesRepositoryImpl
import com.example.myapp4.domain.repositories.useCases.note.DeleteNoteUseCaseImpl
import com.example.myapp4.domain.repositories.useCases.note.GetNotesUseCaseImpl
import com.example.myapp4.domain.repositories.useCases.note.SaveNoteUseCaseImpl
import com.example.myapp4.domain.repositories.useCases.note.UpdateNoteUseCaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val databaseHelper = DatabaseHelper.getInstance(application)
    private val notesDao = databaseHelper.getNotesDao()
    private val notesDataSource = NoteLocalDataSourceImpl(notesDao)
    private val notesRepository = NotesRepositoryImpl(notesDataSource)
    private val getNotesUseCase = GetNotesUseCaseImpl(notesRepository)
    private val saveNoteUseCase = SaveNoteUseCaseImpl(notesRepository)
    private val updateNoteUseCase = UpdateNoteUseCaseImpl(notesRepository)
    private val deleteNoteUseCase = DeleteNoteUseCaseImpl(notesRepository)

    val notes: StateFlow<List<Note>> = getNotesUseCase.getNotes().stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        emptyList()
    )

    fun addNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            saveNoteUseCase.saveNote(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            updateNoteUseCase.updateNote(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteNoteUseCase.deleteNote(note)
        }
    }
}