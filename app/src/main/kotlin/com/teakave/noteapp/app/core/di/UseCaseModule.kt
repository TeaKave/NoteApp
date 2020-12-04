package com.teakave.noteapp.app.core.di

import com.teakave.domain.feature.note.repository.NotesRepository
import com.teakave.domain.feature.note.usecase.ObserveAllNotesUseCase
import com.teakave.domain.feature.note.usecase.RemoveNoteUseCase
import com.teakave.domain.feature.note.usecase.SaveNoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped


@Module
@InstallIn(ActivityRetainedComponent::class)
object UseCaseModule {

    @Provides
    @ActivityRetainedScoped
    fun provideObserveAllNotesUseCase(notesRepository: NotesRepository): ObserveAllNotesUseCase =
        ObserveAllNotesUseCase(notesRepository)

    @Provides
    @ActivityRetainedScoped
    fun provideRemoveNoteUseCase(notesRepository: NotesRepository): RemoveNoteUseCase =
        RemoveNoteUseCase(notesRepository)

    @Provides
    @ActivityRetainedScoped
    fun provideSaveNoteUseCase(notesRepository: NotesRepository): SaveNoteUseCase =
        SaveNoteUseCase(notesRepository)

}
