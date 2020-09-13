package com.teakave.infrastructure.feature.note.source.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.teakave.infrastructure.feature.note.source.local.database.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class NoteDao {

    /**
     * Inserts or updates a note.
     *
     * @param noteEntity Entity for Note [NoteEntity].
     * @return Long value of the inserted rowId.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun saveNote(noteEntity: NoteEntity): Long

    /**
     * Returns Flow of Note Entity list [NoteEntity].
     *
     * @return Flow of Note Entity list or null if doesn't found.
     */
    @Query("SELECT * FROM ${NoteEntity.NOTE_TABLE_NAME}")
    abstract fun observeNotes(): Flow<List<NoteEntity>>

    /**
     * Delete Note Entity by given noteId.
     *
     * @param noteId Note Entity identifier.
     * @return Long value of the deleted rowId.
     */
    @Query("DELETE FROM ${NoteEntity.NOTE_TABLE_NAME} WHERE ${NoteEntity.NOTE_ID} = :noteId")
    abstract suspend fun deleteNote(noteId: Int): Int

}