package com.example.myapp4.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapp4.data.entities.Note
import com.example.myapp4.presentation.LocalNavController
import com.example.myapp4.presentation.viewModels.MainActivityViewModel

@Composable
fun AddNoteScreen(
    noteId: Int? = null,
    viewModel: MainActivityViewModel = viewModel()
) {
    val notes = viewModel.notes.collectAsState()
    val existingNote = noteId?.let { id -> notes.value.find { it.id == id } }

    Column(
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        var noteTitleInput by remember { mutableStateOf(existingNote?.title ?: "") }
        var noteTextInput by remember { mutableStateOf(existingNote?.text ?: "") }
        var isSaving by remember { mutableStateOf(false) }
        val navController = LocalNavController.current

        Text(text = if (existingNote != null) "Edit Note" else "Add Note")
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            value = noteTitleInput,
            onValueChange = { noteTitleInput = it },
            label = { Text("Enter Title") }
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            value = noteTextInput,
            onValueChange = { noteTextInput = it },
            label = { Text("Enter Text") }
        )
        Spacer(modifier = Modifier.height(5.dp))
        Button(onClick = {
            isSaving = true

            val noteTitle = if(noteTitleInput.isNullOrBlank()) "Note Title" else noteTitleInput
            val noteText = if(noteTextInput.isNullOrBlank()) "Note Text" else noteTextInput

            val note = existingNote?.copy(
                title = noteTitle,
                text = noteText
            ) ?: Note(
                title = noteTitle,
                text = noteText
            )
            if (existingNote != null) {
                viewModel.updateNote(note)
            } else {
                viewModel.addNote(note)
            }
            navController.navigateUp()
        },
            enabled = !isSaving
        ) {
            Text("Save")
        }
    }
}