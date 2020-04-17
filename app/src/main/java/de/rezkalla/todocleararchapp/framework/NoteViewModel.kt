package de.rezkalla.todocleararchapp.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import de.rezkalla.core.data.Note
import de.rezkalla.core.repository.NoteRepository
import de.rezkalla.core.usecase.AddNote
import de.rezkalla.core.usecase.GetAllNotes
import de.rezkalla.core.usecase.GetNote
import de.rezkalla.core.usecase.RemoveNote
import de.rezkalla.todocleararchapp.framework.di.ApplicationModule
import de.rezkalla.todocleararchapp.framework.di.DaggerViewModelComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    @Inject
    lateinit var useCases: UseCases

    init {
        DaggerViewModelComponent.builder()
            .applicationModule(ApplicationModule(application))
            .build()
            .inject(this)
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