package de.rezkalla.todocleararchapp.framework

import de.rezkalla.core.usecase.*

data class UseCases(
    val addNoteUseCase: AddNoteUseCase,
    val getAllNotes: GetAllNotes,
    val removeNoteUseCase: RemoveNoteUseCase,
    val getNoteUseCase: GetNoteUseCase
)