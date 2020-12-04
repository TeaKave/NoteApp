package com.teakave.noteapp.app.core.di

import com.teakave.data.feature.note.source.NotesSource
import com.teakave.infrastructure.feature.note.source.local.NotesLocalSourceImpl
import com.teakave.infrastructure.feature.note.source.local.database.dao.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object DataSourceModule {

    @Provides
    @ActivityRetainedScoped
    fun provideNotesSource(noteDao: NoteDao): NotesSource = NotesLocalSourceImpl(noteDao)

}