package com.example.myapp4.presentation.listItems

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.myapp4.data.entities.Note
import com.example.myapp4.presentation.LocalNavController
import com.example.myapp4.presentation.navigation.Directions
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.input.pointer.pointerInput
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun NoteListItem(
    note: Note,
    onClick: () -> Unit = {},
    onLongPress: () -> Unit = {}
) {
    val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        android.util.Log.d("NoteListItem", "Note tapped with ID: ${note.id}")
                        onClick()
                    },
                    onLongPress = {
                        android.util.Log.d("NoteListItem", "Note long pressed with ID: ${note.id}")
                        onLongPress()
                    }
                )
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Title: ${note.title}")
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Text: ${note.text}",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text("Date added: ${formatter.format(Date(note.timeStamp))}")
        Spacer(modifier = Modifier.height(5.dp))
    }
}