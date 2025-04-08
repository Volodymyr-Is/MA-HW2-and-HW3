package com.example.myapp4.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapp4.presentation.LocalNavController
import com.example.myapp4.presentation.viewModels.MainActivityViewModel
import com.example.myapp4.presentation.listItems.NoteListItem
import com.example.myapp4.presentation.navigation.Directions

@Composable
fun NotesScreen(
    viewModel: MainActivityViewModel
) {
    val notes = viewModel.notes.collectAsState()
    val navController = LocalNavController.current
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(notes.value) { note ->
            NoteListItem(
                note = note,
                onClick = {
                    android.util.Log.d("NotesScreen", "Clicked on note with ID: ${note.id}, navigating to: ${Directions.NoteDetail(note.id).route}")
                    navController.navigate(Directions.NoteDetail(note.id).route)
                },
                onLongPress = {
                    viewModel.deleteNote(note)
                }
            )
            HorizontalDivider()
        }
    }
}