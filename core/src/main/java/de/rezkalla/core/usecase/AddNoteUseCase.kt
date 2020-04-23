package de.rezkalla.core.usecase

import de.rezkalla.core.data.Note
import de.rezkalla.core.repository.NoteRepository

class AddNoteUseCase(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(note: Note) = noteRepository.addNote(note)
}
