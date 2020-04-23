package de.rezkalla.todocleararchapp.framework.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import de.rezkalla.todocleararchapp.framework.db.DatabaseService
import javax.inject.Singleton


private  const val DATABASE_NAME = "note.db"
@Module
class DatabaseModule(private val application: Context) {

    private var databaseService: DatabaseService =
        Room.databaseBuilder(application, DatabaseService::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun providesRoomDatabase() = databaseService

    @Singleton
    @Provides
    fun providesNoteDao() = databaseService.noteDao()
}
