package de.rezkalla.todocleararchapp.framework

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.rezkalla.core.data.Note
import de.rezkalla.core.usecase.AddNoteUseCase
import de.rezkalla.core.usecase.GetNoteUseCase
import de.rezkalla.core.usecase.RemoveNoteUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteViewModel @Inject constructor(
    private val addNoteUseCaseUseCase: AddNoteUseCase,
    private val removeNoteUseCaseUseCase: RemoveNoteUseCase,
    private val getNoteUseCase: GetNoteUseCase
) : ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    val saved = MutableLiveData<Boolean>()

    val deleted = MutableLiveData<Boolean>()

    val note = MutableLiveData<Note>()

    fun saveNote(note: Note) {
        coroutineScope.launch {
            addNoteUseCaseUseCase(note)
            saved.postValue(true)
        }
    }

    fun getNote(id: Long) {
        coroutineScope.launch {
            note.postValue(getNoteUseCase.invoke(id))
        }
    }

    fun removeNote(note: Note) {
        coroutineScope.launch {
            removeNoteUseCaseUseCase(note)
            deleted.postValue(true)
        }
    }
}