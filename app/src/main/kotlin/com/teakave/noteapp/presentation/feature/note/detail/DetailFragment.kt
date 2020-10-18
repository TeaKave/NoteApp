package com.teakave.noteapp.presentation.feature.note.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.teakave.domain.feature.note.model.NoteData
import com.teakave.noteapp.R
import com.teakave.noteapp.presentation.feature.common.KeyboardUtil
import com.teakave.noteapp.presentation.feature.note.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_note_detail.edit_text_note_content
import kotlinx.android.synthetic.main.fragment_note_detail.edit_text_note_title
import kotlinx.android.synthetic.main.fragment_note_detail.floating_button_save_note
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.Date

class DetailFragment : Fragment() {

    private val viewModel by sharedViewModel<NoteViewModel>()
    private val keyboardUtil by inject<KeyboardUtil>()

    /**
     * This variable is used for new/old Note identification. If this variable is not null it means that the user is editing an old note.
     *
     * Initialization in [onViewCreated] function.
     */
    private var note: NoteData? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_note_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOnBackPressedListener()
        updateUI(viewModel.selectedNote.value.also { note = it })
        setupOnSaveClickedListener()
    }

    /**
     * Bypass original back press listener.
     *
     * We are handling back press in this fragment so when the user presses back button the note is automatically saved.
     */
    private fun setupOnBackPressedListener() =
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    viewModel.setSelectedNote(null)
                    saveNote()
                    findNavController().navigateUp()
                }
            })

    /**
     * Update note title and content.
     */
    private fun updateUI(noteData: NoteData?) = noteData?.let {
        edit_text_note_title?.setText(it.title)
        edit_text_note_content?.setText(it.content)
    }

    /**
     * Setting up save button click listener.
     *
     * When user clicks save button we are setting up an observer which observes save note result. @see [setupSaveNoteResultObserver].
     *
     * This function calls saveNote function in our viewModel.
     */
    private fun setupOnSaveClickedListener() = floating_button_save_note?.setOnClickListener {
        setupSaveNoteResultObserver()
        saveNote()
    }

    /**
     * This function creates a new [NoteData] object and calls saveNote function in the viewModel.
     */
    private fun saveNote() = viewModel.saveNote(
        NoteData(
            noteId = note?.noteId,
            title = edit_text_note_title?.text.toString(),
            content = edit_text_note_content?.text.toString(),
            createdDate = note?.createdDate ?: Date(),
            lastUpdateDate = Date()
        )
    )

    /**
     * Clear all observers for this viewLifecycleOwner and create a new one which hides keyboard
     * and calls navigateUp if the note was saved successfully, shows an error message otherwise.
     */
    private fun setupSaveNoteResultObserver() {
        viewModel.saveNoteResult.removeObservers(viewLifecycleOwner)
        viewModel.saveNoteResult.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it.isSuccess()) {
                hideKeyboard()
                findNavController().navigateUp()
            } // todo: show a message if we can not save the note
        })
    }

    /**
     * Hides keyboard
     */
    private fun hideKeyboard() = keyboardUtil.hideKeyboard(view)

}
