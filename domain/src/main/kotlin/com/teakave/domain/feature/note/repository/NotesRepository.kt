package com.teakave.domain.feature.note.repository

import com.teakave.domain.Result
import com.teakave.domain.feature.note.model.NoteData
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    /**
     * Observe all notes from local database
     *
     * provides flow of a list of notes
     */
    suspend fun observeAllNotes(): Flow<Result<List<NoteData>>>

    /**
     * Save note to local database
     *
     * @param noteData object of [NoteData]
     */
    suspend fun saveNote(noteData: NoteData): Result<Unit>

    /**
     * Remove note from local database
     *
     * @param noteId identifier of given note
     */
    suspend fun removeNote(noteId: Int): Result<Unit>

}
