package com.example.myapp4.domain.repositories.useCases.note

import com.example.myapp4.data.entities.Note
import kotlinx.coroutines.flow.Flow

interface GetNotesUseCase {

    fun getNotes(): Flow<List<Note>>
}