package com.example.myapp4.domain.repositories.useCases.note

import com.example.myapp4.data.entities.Note
import com.example.myapp4.domain.repositories.NotesRepository

class DeleteNoteUseCaseImpl(
    private val notesRepository: NotesRepository
) : DeleteNoteUseCase {

    override suspend fun deleteNote(note: Note) {
        notesRepository.deleteNote(note)
    }
}