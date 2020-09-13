package com.teakave.domain.feature.note.model

import java.util.Date

/**
 * Note domain model
 *
 * content and title should not be null in the same time
 *
 * @param noteId may be null for new note - Int value otherwise
 * @param title title of the note - may be null
 * @param content content of the note - may be null
 * @param createdDate date when the note was created
 * @param lastUpdateDate date of the latest update (may be same as [createdDate] if note was never edited])
 */
data class NoteData(
    val noteId: Int?,
    val title: String?,
    val content: String?,
    val createdDate: Date,
    val lastUpdateDate: Date
)
