package de.rezkalla.core.usecase

import de.rezkalla.core.data.Note
import de.rezkalla.core.repository.NoteRepository

class RemoveNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) = noteRepository.removeNote(note)
}