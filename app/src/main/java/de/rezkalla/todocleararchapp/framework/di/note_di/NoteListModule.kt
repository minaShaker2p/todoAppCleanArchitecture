package de.rezkalla.todocleararchapp.framework.di.note_di

import dagger.Module
import dagger.Provides
import de.rezkalla.core.repository.NoteRepository
import de.rezkalla.core.usecase.GetAllNotes
import de.rezkalla.todocleararchapp.framework.RoomNoteDataSource
import de.rezkalla.todocleararchapp.framework.db.DatabaseService

@Module
class NoteListModule {

    @Provides
    fun provideRepository(databaseService: DatabaseService) =
        NoteRepository(RoomNoteDataSource(databaseService))

    @Provides
    fun providesGetAllNotes(noteRepository: NoteRepository) = GetAllNotes(noteRepository)


}