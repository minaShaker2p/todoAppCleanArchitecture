package de.rezkalla.todocleararchapp.framework

import de.rezkalla.core.data.Note
import de.rezkalla.core.repository.NoteDataSource
import de.rezkalla.todocleararchapp.framework.db.DatabaseService
import de.rezkalla.todocleararchapp.framework.db.NoteEntity
import de.rezkalla.todocleararchapp.framework.db.toNote
import kotlinx.coroutines.flow.map

class RoomNoteDataSource(databaseService: DatabaseService) : NoteDataSource {

    private val noteDao = databaseService.noteDao()

    override suspend fun addNote(note: Note) = noteDao.addNote(NoteEntity.fromNote(note))

    override suspend fun getNote(id: Long) = noteDao.getNote(id)?.toNote()

    override fun getAll() =
        noteDao.getAllNotes().map { noteList -> noteList.map { note -> note.toNote() } }

    override suspend fun removeNote(note: Note) = noteDao.removeNote(NoteEntity.fromNote(note))
}