package com.teakave.infrastructure.feature.note.source.local

import com.teakave.data.feature.note.source.NotesSource
import com.teakave.domain.ErrorResult
import com.teakave.domain.Result
import com.teakave.domain.feature.note.model.NoteData
import com.teakave.infrastructure.feature.note.source.local.database.dao.NoteDao
import com.teakave.infrastructure.feature.note.source.local.database.mapper.toDataList
import com.teakave.infrastructure.feature.note.source.local.database.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

class NotesLocalSourceImpl(private val noteDao: NoteDao) : NotesSource {

    override suspend fun observeAllNotes(): Flow<Result<List<NoteData>>> =
        noteDao.observeNotes().transform {
            emit(Result.Success(it.toDataList().sortedByDescending { noteData -> noteData.lastUpdateDate }))
        }

    override suspend fun saveNote(noteData: NoteData): Result<Unit> = noteData.toEntity().let {
        if (noteDao.saveNote(it) > 0) {
            Result.Success(Unit)
        } else {
            Result.Error(ErrorResult("Can not save this note"), Unit)
        }
    }

    override suspend fun removeNote(noteId: Int): Result<Unit> =
        if (noteDao.deleteNote(noteId) > 0) {
            Result.Success(Unit)
        } else Result.Error(ErrorResult("Can not delete note by id: $noteId"))

}
