package com.example.myapp4.domain.repositories.useCases.note

import com.example.myapp4.data.entities.Note

interface SaveNoteUseCase {
    suspend fun saveNote(note: Note)
}