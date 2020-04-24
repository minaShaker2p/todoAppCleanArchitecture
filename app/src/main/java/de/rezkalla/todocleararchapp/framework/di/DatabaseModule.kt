package de.rezkalla.todocleararchapp.framework.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import de.rezkalla.todocleararchapp.TodoApplication
import de.rezkalla.todocleararchapp.framework.db.DatabaseService
import javax.inject.Singleton


private const val DATABASE_NAME = "note.db"

@Module
class DatabaseModule() {

    @Singleton
    @Provides
    fun providesRoomDatabase(application: Application) =
        Room.databaseBuilder(application, DatabaseService::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun providesNoteDao(databaseService: DatabaseService) = databaseService.noteDao()
}
