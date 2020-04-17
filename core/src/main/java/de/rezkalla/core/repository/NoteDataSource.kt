package de.rezkalla.core.repository

import de.rezkalla.core.data.Note

interface NoteDataSource {

    suspend fun addNote(note: Note)

    suspend fun getNote(id: Long) :Note ?

    suspend fun getAll(): List<Note>

    suspend fun removeNote(note: Note)
}
