package de.rezkalla.todocleararchapp.presentation

import android.content.Context.INPUT_METHOD_SERVICE
import android.inputmethodservice.InputMethodService
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import de.rezkalla.core.data.Note

import de.rezkalla.todocleararchapp.R
import de.rezkalla.todocleararchapp.framework.NoteViewModel
import kotlinx.android.synthetic.main.fragment_note.*
import kotlinx.android.synthetic.main.item_note.*
import java.util.*

class NoteFragment : Fragment() {

    private lateinit var viewModel: NoteViewModel

    private var currentNote = Note(title = "", content = "", creationTime = 0L, updateTime = 0L)

    private var noteId = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_note, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_note -> {
                if (context != null && currentNote.id != 0L) {
                    AlertDialog.Builder(context!!)
                        .setTitle("Delete a Note")
                        .setMessage("Are you sure you want to delete the Note?")
                        .setPositiveButton("Yes") { dialogInterface, i ->
                            viewModel.removeNote(
                                currentNote
                            )
                        }
                        .setNegativeButton("No") { dialogInterface, i -> }
                        .create()
                        .show()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        arguments?.let {
            noteId = NoteFragmentArgs.fromBundle(it).noteId
        }

        if (noteId != 0L) {
            viewModel.getNote(noteId)
        }
        checkButton.setOnClickListener {
            if (edtTitle.text.toString().isNotEmpty() && edtContent.text.toString().isNotEmpty()) {
                val time = System.currentTimeMillis()
                currentNote.title = edtTitle.text.toString()
                currentNote.content = edtContent.text.toString()
                currentNote.updateTime = time
                if (currentNote.id == 0L) currentNote.creationTime = time
                viewModel.saveNote(currentNote)
            } else {
                Navigation.findNavController(it).popBackStack()
            }
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.saved.observe(this, Observer {
            if (it) {
                hideSystemKeyboard()
                Toast.makeText(context, "Done!!", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(edtTitle).popBackStack()
            } else {
                Toast.makeText(
                    context,
                    "Something went wrong , please try again!!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        viewModel.note.observe(this, Observer { note ->
            currentNote = note
            edtTitle.setText(currentNote.title, TextView.BufferType.EDITABLE)
            edtContent.setText(currentNote.content, TextView.BufferType.EDITABLE)
        })

        viewModel.deleted.observe(this, Observer {
            if (it) Navigation.findNavController(edtTitle).popBackStack()
        })
    }

    private fun hideSystemKeyboard() {
        val imm = context?.getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(edtTitle.windowToken, 0)
    }


}
