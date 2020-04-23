package de.rezkalla.todocleararchapp.framework.di

import android.content.Context
import dagger.Module
import dagger.Provides
import de.rezkalla.core.repository.NoteRepository
import de.rezkalla.todocleararchapp.framework.RoomNoteDataSource
import de.rezkalla.todocleararchapp.framework.db.DatabaseService

@Module
class RepositoryModule {

    @Provides
    fun provideRepository(databaseService: DatabaseService) =
        NoteRepository(RoomNoteDataSource(databaseService))

}