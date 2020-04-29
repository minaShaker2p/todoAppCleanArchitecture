package de.rezkalla.todocleararchapp.framework

import de.rezkalla.core.usecase.*

data class UseCases(
    val addNoteUseCase: AddNoteUseCase,
    val getAllNotesUseCase: GetAllNotesUseCase,
    val removeNoteUseCase: RemoveNoteUseCase,
    val getNoteUseCase: GetNoteUseCase
)