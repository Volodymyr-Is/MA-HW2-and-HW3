package com.example.myapp4.domain.repositories.useCases.note

import com.example.myapp4.data.entities.Note

interface DeleteNoteUseCase {
    suspend fun deleteNote(note: Note)
}