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

class ListViewModel(application: Application) : AndroidViewModel(application) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    @Inject
    lateinit var useCases: UseCases

    init {
        DaggerViewModelComponent.builder()
            .applicationModule(ApplicationModule(application))
            .build()
            .inject(this)
    }

    val notes = MutableLiveData<List<Note>>()

    fun getNotes() {
        coroutineScope.launch {
            val noteList = useCases.getAllNotes.invoke()
            noteList.forEach { it.wordCount = useCases.wordCount.invoke(it) }
            notes.postValue(noteList)

        }
    }

}