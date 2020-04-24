package de.rezkalla.todocleararchapp.framework.di.note_di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.rezkalla.todocleararchapp.framework.di.AddNoteModule
import de.rezkalla.todocleararchapp.presentation.NoteFragment

@Module
abstract class NoteFragmentBuilder {

    @ContributesAndroidInjector(modules = [AddNoteModule::class])
    internal abstract fun providesNoteFragment(): NoteFragment
}