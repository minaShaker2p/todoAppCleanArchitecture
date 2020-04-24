package de.rezkalla.todocleararchapp.framework.di.note_di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.rezkalla.todocleararchapp.presentation.ListFragment

@Module
abstract class NoteListFragmentBuilder {

    @ContributesAndroidInjector(modules = [NoteListModule::class])
    internal abstract fun providesNoteListFragment(): ListFragment
}