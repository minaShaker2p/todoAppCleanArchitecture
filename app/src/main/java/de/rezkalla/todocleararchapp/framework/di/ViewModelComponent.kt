package de.rezkalla.todocleararchapp.framework.di

import dagger.Component
import de.rezkalla.todocleararchapp.framework.ListViewModel
import de.rezkalla.todocleararchapp.framework.NoteViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, DatabaseModule::class, RepositoryModule::class, UseCasesModule::class])
interface ViewModelComponent {

    fun inject(noteViewModel: NoteViewModel)

    fun inject(listViewModel: ListViewModel)
}