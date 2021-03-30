package com.teakave.domain.feature.note.usecase

import com.arch.domain.ErrorResult
import com.arch.domain.Result
import com.arch.domain.usecases.UseCaseResult
import com.teakave.domain.feature.note.repository.NotesRepository

/**
 * Remove note UseCase returns Result.Success if note is deleted, Result.Error otherwise
 *
 * Parameter params: note identifier - Int value
 */
class RemoveNoteUseCase(private val notesRepository: NotesRepository) : UseCaseResult<Unit, Int>() {

    override suspend fun doWork(params: Int): Result<Unit> = if (params < 0) {
        Result.Error(ErrorResult("Can not remove the note with this id: $params"))
    } else notesRepository.removeNote(params)

}
