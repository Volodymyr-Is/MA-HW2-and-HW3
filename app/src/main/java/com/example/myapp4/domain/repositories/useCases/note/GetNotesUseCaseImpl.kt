package com.example.myapp4.domain.repositories.useCases.note

import com.example.myapp4.data.entities.Note
import com.example.myapp4.domain.repositories.NotesRepository
import kotlinx.coroutines.flow.Flow

class GetNotesUseCaseImpl(
    private val notesRepository: NotesRepository
) : GetNotesUseCase {

    override fun getNotes(): Flow<List<Note>> {
        return notesRepository.getNotes()
    }
}