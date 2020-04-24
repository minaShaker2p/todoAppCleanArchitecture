package de.rezkalla.todocleararchapp.framework.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.rezkalla.todocleararchapp.framework.di.note_di.NoteFragmentBuilder
import de.rezkalla.todocleararchapp.framework.di.note_di.NoteListFragmentBuilder
import de.rezkalla.todocleararchapp.presentation.MainActivity

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [NoteListFragmentBuilder::class, NoteFragmentBuilder::class])
    abstract fun bindMainActivity(): MainActivity
}