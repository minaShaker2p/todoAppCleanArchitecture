package de.rezkalla.todocleararchapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import de.rezkalla.core.data.Note
import de.rezkalla.todocleararchapp.R
import kotlinx.android.synthetic.main.item_note.view.*
import java.text.SimpleDateFormat
import java.util.*

class NoteListAdapter(private val noteList: ArrayList<Note>, val action: ListAction) :
    RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    fun updateNotes(notes: List<Note>) {
        noteList.clear()
        noteList.addAll(notes)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder((view))
    }

    override fun getItemCount() = noteList.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(noteList[holder.adapterPosition])
    }

    inner class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val layout = view.layout_note
        private val title = view.tv_title
        private val content = view.tv_content
        private val updateTime = view.tv_update_time
        private val wordCount = view.tv_word_count

        fun bind(note: Note) {
            title.text = note.title
            content.text = note.content
            val sdf = SimpleDateFormat("MMM dd, HH:mm:ss", Locale.GERMANY)
            val date = Date(note.updateTime)
            updateTime.text = sdf.format(date)
            wordCount.text = "Words: ${note.wordCount}"
            layout.setOnClickListener {
                action.onClick(note.id)
            }
        }
    }
}