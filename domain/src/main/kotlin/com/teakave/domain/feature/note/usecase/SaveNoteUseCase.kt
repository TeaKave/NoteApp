package com.teakave.domain.feature.note.usecase

import com.teakave.domain.ErrorResult
import com.teakave.domain.Result
import com.teakave.domain.feature.note.model.NoteData
import com.teakave.domain.feature.note.repository.NotesRepository
import com.teakave.domain.usecases.UseCaseResult

/**
 * Save note UseCase returns Result.Success if note is saved or updated, Result.Error otherwise
 *
 * Parameter params: note - new or existing [NoteData]
 */
class SaveNoteUseCase(private val notesRepository: NotesRepository) :
    UseCaseResult<Unit, NoteData>() {

    override suspend fun doWork(params: NoteData): Result<Unit> =
        if (params.content.isNullOrEmpty() && params.title.isNullOrEmpty()) {
            Result.Error(
                ErrorResult("Can not save this note. Note content and title is empty"),
                Unit
            )
        } else notesRepository.saveNote(params)

}
