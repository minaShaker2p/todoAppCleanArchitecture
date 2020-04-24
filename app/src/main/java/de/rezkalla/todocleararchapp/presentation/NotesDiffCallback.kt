package de.rezkalla.todocleararchapp.presentation

import androidx.recyclerview.widget.DiffUtil
import de.rezkalla.core.data.Note

/**
 * this implementation based on this article
 * https://android.jlelse.eu/smart-way-to-update-recyclerview-using-diffutil-345941a160e0
 */

class NotesDiffCallback(private val oldNoteList: List<Note>, private val newNoteList: List<Note>) :
    DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldNoteList[oldItemPosition].id == newNoteList[newItemPosition].id
    }

    override fun getOldListSize() = oldNoteList.size

    override fun getNewListSize() = newNoteList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldNoteList[oldItemPosition] == newNoteList[newItemPosition]
    }
}
