package com.teakave.infrastructure.feature.note.source.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.teakave.infrastructure.feature.note.source.local.database.entity.NoteEntity.Companion.NOTE_TABLE_NAME
import java.util.*

@Entity(tableName = NOTE_TABLE_NAME)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = NOTE_ID)
    val noteId: Int,
    @ColumnInfo(name = TITLE)
    val title: String?,
    @ColumnInfo(name = CONTENT)
    val content: String?,
    @ColumnInfo(name = CREATED_DATE)
    val createdDate: Date,
    @ColumnInfo(name = UPDATED_DATE)
    val lastUpdateDate: Date
) {
    companion object {
        /**
         * Table name for the DB of this entity
         */
        const val NOTE_TABLE_NAME = "noteTable"
        const val NOTE_ID = "noteId"
        const val TITLE = "title"
        const val CONTENT = "content"
        const val CREATED_DATE = "createdDate"
        const val UPDATED_DATE = "updatedDate"
    }
}
