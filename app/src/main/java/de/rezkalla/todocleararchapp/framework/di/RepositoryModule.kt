package de.rezkalla.todocleararchapp.framework.di

import android.app.Application
import dagger.Module
import dagger.Provides
import de.rezkalla.core.repository.NoteRepository
import de.rezkalla.todocleararchapp.framework.RoomNoteDataSource

@Module
class RepositoryModule {

    @Provides
    fun provideRepository(app: Application) = NoteRepository(RoomNoteDataSource(app))

}