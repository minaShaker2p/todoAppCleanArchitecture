package de.rezkalla.core.repository

import de.rezkalla.core.data.Note
import kotlinx.coroutines.flow.*

interface NoteDataSource {

    suspend fun addNote(note: Note)

    suspend fun getNote(id: Long): Note?

    fun getAll(): Flow<List<Note>>

    suspend fun removeNote(note: Note)
}
