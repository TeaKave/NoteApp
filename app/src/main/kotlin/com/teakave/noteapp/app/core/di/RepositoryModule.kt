package com.teakave.noteapp.app.core.di

import com.teakave.data.feature.note.repository.NotesRepositoryImpl
import com.teakave.data.feature.note.source.NotesSource
import com.teakave.domain.feature.note.repository.NotesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideNotesRepository(notesSource: NotesSource): NotesRepository =
        NotesRepositoryImpl(notesSource)

}
