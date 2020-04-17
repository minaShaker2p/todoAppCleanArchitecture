package de.rezkalla.todocleararchapp.framework.di

import dagger.Component
import de.rezkalla.todocleararchapp.framework.ListViewModel
import de.rezkalla.todocleararchapp.framework.NoteViewModel

@Component(modules = [ApplicationModule::class, RepositoryModule::class, UseCasesModule::class])
interface ViewModelComponent {
    fun inject(noteViewModel: NoteViewModel)

    fun inject(listViewModel: ListViewModel)

}