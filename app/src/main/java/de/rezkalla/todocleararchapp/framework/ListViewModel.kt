package de.rezkalla.todocleararchapp.framework

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.rezkalla.core.data.Note
import de.rezkalla.todocleararchapp.TodoApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListViewModel : ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    @Inject
    lateinit var useCases: UseCases

    init {
        TodoApplication.getViewModelComponent()?.inject(this)
        getNotes()
    }

    val notes = MutableLiveData<List<Note>>()

    private fun getNotes() {
        coroutineScope.launch {
            val noteList = useCases.getAllNotes.invoke()
            noteList.collect { list ->
                list.forEach { it.wordCount = useCases.wordCount.invoke(it) }
                notes.postValue(list)
            }
        }
    }
}