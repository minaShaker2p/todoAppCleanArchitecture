package de.rezkalla.todocleararchapp.framework

import de.rezkalla.core.usecase.*

data class UseCases(
    val addNote: AddNote,
    val getAllNotes: GetAllNotes,
    val removeNote: RemoveNote,
    val getNote: GetNote
)