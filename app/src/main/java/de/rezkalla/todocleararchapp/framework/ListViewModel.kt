package de.rezkalla.todocleararchapp.framework

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.rezkalla.core.data.Note
import de.rezkalla.core.usecase.GetAllNotes
import de.rezkalla.core.usecase.SortType
import de.rezkalla.todocleararchapp.TodoApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListViewModel @Inject constructor(var getAllNotes: GetAllNotes) : ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    init {
        getNotes()
    }

    val notes = MutableLiveData<List<Note>>()

    private fun getNotes() {
        coroutineScope.launch {
            val noteList = getAllNotes.invoke(SortType.DEC)
            noteList.collect {
                notes.postValue(it)
            }
        }
    }
}