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
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val noteAppModule = module {

    factory { ObserveAllNotesUseCase(get()) }

    factory { RemoveNoteUseCase(get()) }

    factory { SaveNoteUseCase(get()) }

    single<NotesRepository> { NotesRepositoryImpl(get()) }

    single<NotesSource> { NotesLocalSourceImpl(get<AppDatabase>().noteDao) }

    single<AppDatabase> {
        BaseDatabase.buildDatabase(androidContext(), AppDatabase.DB_NAME, null, null)
    }

}