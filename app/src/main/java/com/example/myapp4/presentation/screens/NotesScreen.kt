package com.example.myapp4.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)
    ) {
        items(notes.value) { note ->
            NoteListItem(
                note = note,
                onClick = {
                    navController.navigate(Directions.NoteDetail(note.id).route)
                },
                onLongPress = {
                    viewModel.deleteNote(note)
                }
            )
        }
    }
}