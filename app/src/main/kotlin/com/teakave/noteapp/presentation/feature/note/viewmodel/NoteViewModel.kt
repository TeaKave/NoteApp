package com.teakave.noteapp.presentation.feature.note.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arch.domain.Result
import com.teakave.domain.feature.note.model.NoteData
import com.teakave.domain.feature.note.usecase.ObserveAllNotesUseCase
import com.teakave.domain.feature.note.usecase.RemoveNoteUseCase
import com.teakave.domain.feature.note.usecase.SaveNoteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

class NoteViewModel(
    private val observeAllNotesUseCase: ObserveAllNotesUseCase,
    private val saveNoteUseCase: SaveNoteUseCase,
    private val removeNoteUseCase: RemoveNoteUseCase
) : ViewModel(), KoinComponent {

    private val _notes = MutableLiveData<Result<List<NoteData>>>()
    val notes: LiveData<Result<List<NoteData>>> = _notes

    private val _selectedNote = MutableLiveData<NoteData?>()
    val selectedNote: LiveData<NoteData?> = _selectedNote

    private val _saveNoteResult = MutableLiveData<Result<Unit>>()
    val saveNoteResult = _saveNoteResult

    /**
     * Observes all notes in local repository.
     */
    fun observeAllNotes() = viewModelScope.launch(Dispatchers.IO) {
        observeAllNotesUseCase().collect { allNotesResult ->
            _notes.postValue(allNotesResult)
        }
    }

    /**
     * Sets selected note.
     *
     * @param noteData - nullable [NoteData] object.
     */
    fun setSelectedNote(noteData: NoteData?) = _selectedNote.postValue(noteData)

    /**
     * Calls remove note useCase.
     *
     * @param noteId - nullable identifier of a note.
     */
    fun removeNote(noteId: Int?) = viewModelScope.launch(Dispatchers.IO) {
        noteId?.let {
            removeNoteUseCase(it)
        }
    }

    /**
     * Calls save note useCase.
     *
     * @param noteData - not nullable object of [NoteData]
     */
    fun saveNote(noteData: NoteData) = viewModelScope.launch(Dispatchers.IO) {
        _saveNoteResult.postValue(saveNoteUseCase(noteData))
    }

}
