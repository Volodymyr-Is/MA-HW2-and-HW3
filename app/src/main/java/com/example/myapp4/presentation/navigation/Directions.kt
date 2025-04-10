package com.example.myapp4.presentation.navigation

sealed class Directions(val route: String) {
    data class NotesList(val direction: String = "notes_list") : Directions(direction)
    data class AddNote(val noteId: Int? = null) : Directions(
        if (noteId != null) "add_note/$noteId" else "add_note"
    )
    data class NoteDetail(val noteId: Int = 0) : Directions("note_detail/$noteId")

    companion object {
        const val NOTE_DETAIL_ROUTE = "note_detail/{noteId}"
    }
}