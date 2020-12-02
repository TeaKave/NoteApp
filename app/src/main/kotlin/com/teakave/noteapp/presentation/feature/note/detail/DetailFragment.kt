package com.teakave.noteapp.presentation.feature.note.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.teakave.domain.feature.note.model.NoteData
import com.teakave.noteapp.databinding.FragmentNoteDetailBinding
import com.teakave.noteapp.presentation.feature.common.KeyboardUtil
import com.teakave.noteapp.presentation.feature.note.viewmodel.NoteViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.text.SimpleDateFormat
import java.util.Date

const val LAST_MODIFIED_DATE_FORMAT = "dd.MMMM.yyyy HH:mm"

class DetailFragment : Fragment() {

    private val viewModel by sharedViewModel<NoteViewModel>()
    private val keyboardUtil by inject<KeyboardUtil>()

    private var _binding: FragmentNoteDetailBinding? = null
    private val binding get() = _binding!!

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
    ): View? {
        _binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

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
                    if (contentChanged()) {
                        saveNote()
                    }
                    navigateUp()
                }
            })

    /**
     * Update note title, content and timestamp.
     */
    @SuppressLint("SimpleDateFormat")
    private fun updateUI(noteData: NoteData?) = noteData?.let {
        binding.apply {
            editTextNoteTitle.setText(it.title)
            editTextNoteContent.setText(it.content)
            textNoteLastUpdate.apply {
                isVisible = true
                text = SimpleDateFormat(LAST_MODIFIED_DATE_FORMAT).format(it.lastUpdateDate)
            }
        }
    } ?: run {
        binding.textNoteLastUpdate.isInvisible = true
    }

    /**
     * Setting up save button click listener.
     *
     * When user clicks save button we are setting up an observer which observes save note result. @see [setupSaveNoteResultObserver].
     *
     * This function calls saveNote function in our viewModel.
     */
    private fun setupOnSaveClickedListener() = binding.floatingButtonSaveNote.setOnClickListener {
        if (contentChanged()) {
            setupSaveNoteResultObserver()
            saveNote()
        } else {
            viewModel.setSelectedNote(null)
            navigateUp()
        }
    }

    /**
     * This function creates a new [NoteData] object and calls saveNote function in the viewModel.
     */
    private fun saveNote() = viewModel.saveNote(
        NoteData(
            noteId = note?.noteId,
            title = binding.editTextNoteTitle.text.toString(),
            content = binding.editTextNoteContent.text.toString(),
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
                navigateUp()
            } // todo: show a message if we can not save the note
        })
    }

    /**
     * Determine if user changed the original content of the note.
     *
     * Comparing title and detail.
     */
    private fun contentChanged() = (binding.editTextNoteTitle.text.toString() != note?.title
            || binding.editTextNoteContent.text.toString() != note?.content)

    /**
     * Hides keyboard
     */
    private fun hideKeyboard() = keyboardUtil.hideKeyboard(view)

    private fun navigateUp() = findNavController().navigateUp()

}
