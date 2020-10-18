package com.teakave.noteapp.app.core.di

import com.teakave.data.feature.note.repository.NotesRepositoryImpl
import com.teakave.data.feature.note.source.NotesSource
import com.teakave.domain.feature.note.repository.NotesRepository
import com.teakave.domain.feature.note.usecase.ObserveAllNotesUseCase
import com.teakave.domain.feature.note.usecase.RemoveNoteUseCase
import com.teakave.domain.feature.note.usecase.SaveNoteUseCase
import com.teakave.infrastructure.feature.common.database.AppDatabase
import com.teakave.infrastructure.feature.common.database.BaseDatabase
import com.teakave.infrastructure.feature.note.source.local.NotesLocalSourceImpl
import com.teakave.noteapp.presentation.feature.common.KeyboardUtil
import com.teakave.noteapp.presentation.feature.note.viewmodel.NoteViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val noteAppModule = module {

    viewModel { NoteViewModel(get(), get(), get()) }

    factory { ObserveAllNotesUseCase(get()) }

    factory { RemoveNoteUseCase(get()) }

    factory { SaveNoteUseCase(get()) }

    factory { KeyboardUtil() }

    single<NotesRepository> { NotesRepositoryImpl(get()) }

    single<NotesSource> { NotesLocalSourceImpl(get<AppDatabase>().noteDao) }

    single<AppDatabase> {
        BaseDatabase.buildDatabase(androidContext(), AppDatabase.DB_NAME, null, null)
    }

}