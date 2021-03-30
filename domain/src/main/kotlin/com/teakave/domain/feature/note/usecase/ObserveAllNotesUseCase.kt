package com.teakave.domain.feature.note.usecase

import com.arch.domain.Result
import com.arch.domain.usecases.UseCaseNoParams
import com.teakave.domain.feature.note.model.NoteData
import com.teakave.domain.feature.note.repository.NotesRepository
import kotlinx.coroutines.flow.Flow

/**
 * Observe notes UseCase
 *
 * observe all notes from local database
 */
class ObserveAllNotesUseCase(private val notesRepository: NotesRepository) :
    UseCaseNoParams<Flow<Result<List<NoteData>>>>() {

    override suspend fun doWork(params: Unit): Flow<Result<List<NoteData>>> =
        notesRepository.observeAllNotes()


}
