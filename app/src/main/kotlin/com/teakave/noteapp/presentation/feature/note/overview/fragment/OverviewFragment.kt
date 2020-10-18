package com.teakave.noteapp.presentation.feature.note.overview.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.teakave.noteapp.R
import com.teakave.noteapp.presentation.feature.note.overview.adapter.NotesAdapter
import com.teakave.noteapp.presentation.feature.note.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_overview.button_add_note
import kotlinx.android.synthetic.main.fragment_overview.recycler_view_notes
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class OverviewFragment : Fragment() {

    private val viewModel: NoteViewModel by sharedViewModel()
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_overview, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOnBacPressedListener()
        setupNotesAdapter()
        setupAddNoteButtonListener()
    }

    private fun setupOnBacPressedListener() =
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    // overriding activity.onBackPressed to avoid memory leak on Android Q
                    requireActivity().finishAfterTransition()
                }
            })

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeAllNotes()
        observeSelectedNote()
    }

    override fun onDestroyView() {
        // set recycler view adapter null so it does not cause memory leak
        recycler_view_notes?.adapter = null
        super.onDestroyView()
    }

    /**
     * Sets up observer for all notes.
     *
     * Updates notes in recyclerView.
     */
    private fun observeAllNotes() {
        viewModel.notes.observe(viewLifecycleOwner, Observer { notesResult ->
            if (::notesAdapter.isInitialized) {
                notesResult.getOrNull()?.let {
                    notesAdapter.setNotes(it)
                }
            }
        })
        viewModel.observeAllNotes()
    }

    /**
     * Sets up observer for selected note.
     *
     * Navigates to note detail fragment if note is not null.
     */
    private fun observeSelectedNote() =
        viewModel.selectedNote.observe(viewLifecycleOwner, Observer {
            // noteData could be null when returning from [DetailFragment]
            if (it != null) {
                showNoteDetailFragment()
            }
        })

    /**
     * Sets up notes adapter.
     */
    private fun setupNotesAdapter() {
        notesAdapter = NotesAdapter(
            clickListener = {
                viewModel.setSelectedNote(it)
            },
            deleteClickListener = {
                viewModel.removeNote(it)
            })
        recycler_view_notes?.adapter = notesAdapter
    }

    /**
     * Add note button navigates to note detail fragment with no selected note.
     */
    private fun setupAddNoteButtonListener() = button_add_note.setOnClickListener {
        showNoteDetailFragment()
    }

    /**
     * Navigates to detail fragment.
     */
    private fun showNoteDetailFragment() =
        findNavController().navigate(OverviewFragmentDirections.toDetailFragment())

}
