package com.teakave.infrastructure.feature.note.source.local.database.mapper

import com.teakave.domain.feature.note.model.NoteData
import com.teakave.infrastructure.feature.note.source.local.database.entity.NoteEntity

fun NoteData.toEntity() = noteId?.let {
    NoteEntity(it, title, content, createdDate, lastUpdateDate)
}

fun List<NoteEntity>.toDataList(): List<NoteData> {
    val result = ArrayList<NoteData>()
    this.forEach {
        result.add(it.toData())
    }
    return result
}

fun NoteEntity.toData() = NoteData(noteId, title, content, createdDate, lastUpdateDate)