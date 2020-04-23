package de.rezkalla.todocleararchapp.framework.di

import dagger.Module
import dagger.Provides
import de.rezkalla.core.repository.NoteRepository
import de.rezkalla.core.usecase.AddNoteUseCase
import de.rezkalla.core.usecase.GetNoteUseCase
import de.rezkalla.core.usecase.RemoveNoteUseCase
import de.rezkalla.todocleararchapp.framework.RoomNoteDataSource
import de.rezkalla.todocleararchapp.framework.db.DatabaseService

@Module
class AddNoteModule {

    @Provides
    fun provideRepository(databaseService: DatabaseService) =
        NoteRepository(RoomNoteDataSource(databaseService))

    @Provides
    fun providesAddNoteUseCase(noteRepository: NoteRepository) =
        AddNoteUseCase(noteRepository)

    @Provides
    fun providesRemoveNoteUseCase(noteRepository: NoteRepository) =
        RemoveNoteUseCase(noteRepository)

    @Provides
    fun providesGetNoteUseCase(noteRepository: NoteRepository) =
        GetNoteUseCase(noteRepository)
}