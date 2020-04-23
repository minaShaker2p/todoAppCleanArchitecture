package de.rezkalla.todocleararchapp.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import de.rezkalla.core.data.Note

@Entity(tableName = "note")
data class NoteEntity(
    val title: String,
    val content: String,
    @ColumnInfo(name = "create_time")
    val createTime: Long,
    @ColumnInfo(name = "update_time")
    val updateTime: Long,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L
) {
    companion object {
        fun fromNote(note: Note) = NoteEntity(
            title = note.title,
            content = note.content,
            createTime = note.creationTime,
            updateTime = note.updateTime,
            id = note.id
        )
    }
}

fun NoteEntity.toNote() = Note(title, content, createTime, updateTime, id)
