package de.rezkalla.core.repository

import de.rezkalla.core.data.Note

class NoteRepository(private val dataSource: NoteDataSource) {

    suspend fun addNote(note: Note) = dataSource.addNote(note)

    suspend fun getNote(id: Long) = dataSource.getNote(id)

     fun getAllNotes() = dataSource.getAll()

    suspend fun removeNote(note: Note) = dataSource.removeNote(note)
}
