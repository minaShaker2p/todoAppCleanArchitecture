package de.rezkalla.todocleararchapp.framework

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.rezkalla.core.data.Note
import de.rezkalla.todocleararchapp.TodoApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteViewModel : ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    @Inject
    lateinit var useCases: UseCases

    init {
        TodoApplication.getViewModelComponent()?.inject(this)
    }

    val saved = MutableLiveData<Boolean>()

    val deleted = MutableLiveData<Boolean>()

    val note = MutableLiveData<Note>()

    fun saveNote(note: Note) {
        coroutineScope.launch {
            useCases.addNote(note)
            saved.postValue(true)
        }
    }

    fun getNote(id: Long) {
        coroutineScope.launch {
            note.postValue(useCases.getNote.invoke(id))
        }
    }

    fun removeNote(note: Note) {
        coroutineScope.launch {
            useCases.removeNote(note)
            deleted.postValue(true)
        }
    }
}