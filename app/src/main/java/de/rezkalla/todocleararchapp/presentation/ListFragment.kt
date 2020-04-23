package de.rezkalla.todocleararchapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.AndroidSupportInjection
import de.rezkalla.todocleararchapp.R
import de.rezkalla.todocleararchapp.ViewModelFactory
import de.rezkalla.todocleararchapp.framework.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class ListFragment : Fragment(), ListAction {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<ListViewModel>

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(ListViewModel::class.java)
    }


    private val notesAdapter = NoteListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AndroidSupportInjection.inject(this)
        NotesList.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = notesAdapter
        }

        addNoteButton.setOnClickListener {
            navigateToNoteDetails()
        }
        observeViewModel()

    }


    private fun observeViewModel() {
        viewModel.notes.observe(this, Observer { noteList ->
            progressBar.visibility = View.GONE
            NotesList.visibility = View.VISIBLE
            notesAdapter.updateNotes(noteList)
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
