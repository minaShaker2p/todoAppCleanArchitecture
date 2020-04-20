package de.rezkalla.todocleararchapp.framework.di

import dagger.Module
import dagger.Provides
import de.rezkalla.core.repository.NoteRepository
import de.rezkalla.core.usecase.*
import de.rezkalla.todocleararchapp.framework.UseCases

@Module
class UseCasesModule {

    @Provides
    fun provideUseCases(repository: NoteRepository) = UseCases(
        AddNote(repository),
        GetAllNotes(repository),
        RemoveNote(repository),
        GetNote(repository),
        GetWordCount()
    )
}
