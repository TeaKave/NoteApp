package com.teakave.infrastructure.feature.common.database

import androidx.room.Database
import androidx.room.TypeConverters
import com.teakave.infrastructure.feature.common.database.converter.DateConverter
import com.teakave.infrastructure.feature.note.source.local.database.dao.NoteDao
import com.teakave.infrastructure.feature.note.source.local.database.entity.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : BaseDatabase() {
    abstract val noteDao: NoteDao

    companion object {
        const val DB_NAME = "note-database"
    }
}
