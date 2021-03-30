package com.teakave.data.feature.note.repository

import com.teakave.data.feature.note.source.NotesSource
import com.arch.domain.Result
import com.teakave.domain.feature.note.model.NoteData
import com.teakave.domain.feature.note.repository.NotesRepository
import kotlinx.coroutines.flow.Flow

class NotesRepositoryImpl(private val notesLocalSource: NotesSource) : NotesRepository {

    override suspend fun observeAllNotes(): Flow<Result<List<NoteData>>> =
        notesLocalSource.observeAllNotes()

    override suspend fun saveNote(noteData: NoteData): Result<Unit> =
        notesLocalSource.saveNote(noteData)

    override suspend fun removeNote(noteId: Int): Result<Unit> = notesLocalSource.removeNote(noteId)

}
