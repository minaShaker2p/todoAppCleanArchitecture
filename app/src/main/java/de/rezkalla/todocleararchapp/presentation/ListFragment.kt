package de.rezkalla.todocleararchapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import de.rezkalla.todocleararchapp.R
import de.rezkalla.todocleararchapp.TodoApplication
import de.rezkalla.todocleararchapp.framework.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment(), ListAction {

    private lateinit var viewModel: ListViewModel

    private val notesAdapter = NoteListAdapter(arrayListOf(), this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        NotesList.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = notesAdapter
        }
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

        addNoteButton.setOnClickListener {
            navigateToNoteDetails()
        }
        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.notes.observe(this, Observer { noteList ->
            progressBar.visibility = View.GONE
            NotesList.visibility = View.VISIBLE
            notesAdapter.updateNotes(noteList.sortedByDescending { it.updateTime })
        })
    }

    private fun navigateToNoteDetails(id: Long = 0) {
        val action = ListFragmentDirections.actionAddNote(noteId = id)
        Navigation.findNavController(NotesList).navigate(action)
    }

    override fun onClick(id: Long) {
        navigateToNoteDetails(id)
    }
}
