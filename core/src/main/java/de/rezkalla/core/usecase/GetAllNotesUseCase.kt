package de.rezkalla.core.usecase

import de.rezkalla.core.data.Note
import de.rezkalla.core.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllNotes(private val noteRepository: NoteRepository) {

    operator fun invoke(sortType: SortType): Flow<List<Note>> {
        return if (sortType == SortType.DEC)
            noteRepository.getAllNotes().map { notes -> notes.sortedByDescending { it.updateTime } }
        else
            noteRepository.getAllNotes()
    }
}

enum class SortType {
    ASC,
    DEC
}