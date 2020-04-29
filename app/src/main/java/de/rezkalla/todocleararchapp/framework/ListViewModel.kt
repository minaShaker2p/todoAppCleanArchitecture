package de.rezkalla.todocleararchapp.framework

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.rezkalla.core.data.Note
import de.rezkalla.core.usecase.GetAllNotesUseCase
import de.rezkalla.core.usecase.SortType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListViewModel @Inject constructor(var getAllNotesUseCase: GetAllNotesUseCase) : ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    init {
        getNotes()
    }

    val notes = MutableLiveData<List<Note>>()

    private fun getNotes() {
        coroutineScope.launch {
            getAllNotesUseCase.invoke(SortType.DEC).collect {
                notes.postValue(it)
            }
        }
    }
}