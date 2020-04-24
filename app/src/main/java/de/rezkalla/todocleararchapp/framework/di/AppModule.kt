package de.rezkalla.todocleararchapp.framework.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import de.rezkalla.todocleararchapp.TodoApplication
import javax.inject.Singleton


@Module
class AppModule() {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application.applicationContext
}
