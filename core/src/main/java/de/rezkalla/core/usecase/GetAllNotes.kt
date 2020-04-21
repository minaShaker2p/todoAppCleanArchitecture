package de.rezkalla.core.usecase

import de.rezkalla.core.repository.NoteRepository

class GetAllNotes(private val noteRepository: NoteRepository) {

     operator fun invoke() = noteRepository.getAllNotes()
}