package com.example.myapp4.domain.repositories.useCases.note

import com.example.myapp4.data.entities.Note
import com.example.myapp4.domain.repositories.NotesRepository

class UpdateNoteUseCaseImpl(
    private val notesRepository: NotesRepository
) : UpdateNoteUseCase {

    override suspend fun updateNote(note: Note) {
        notesRepository.updateNote(note)
    }
}