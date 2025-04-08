package com.example.myapp4.domain.repositories.useCases.note

import com.example.myapp4.data.entities.Note
import com.example.myapp4.domain.repositories.NotesRepository

class SaveNoteUseCaseImpl(
    private val notesRepository: NotesRepository
) : SaveNoteUseCase  {

    override suspend fun saveNote(note: Note) {
        notesRepository.addNote(note)
    }

}