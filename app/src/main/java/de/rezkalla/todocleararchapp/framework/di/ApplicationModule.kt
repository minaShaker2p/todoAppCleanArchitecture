package de.rezkalla.todocleararchapp.framework.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application: Context) {

    @Provides
    fun provideContext() = application
}
