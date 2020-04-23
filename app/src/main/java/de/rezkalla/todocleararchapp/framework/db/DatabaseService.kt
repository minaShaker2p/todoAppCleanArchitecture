package de.rezkalla.todocleararchapp.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class], version = 1)
abstract class DatabaseService : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}