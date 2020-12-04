package com.teakave.noteapp.app.core.di

import android.content.Context
import com.teakave.infrastructure.feature.common.database.AppDatabase
import com.teakave.infrastructure.feature.common.database.BaseDatabase
import com.teakave.infrastructure.feature.note.source.local.database.dao.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase =
        BaseDatabase.buildDatabase(appContext, AppDatabase.DB_NAME, null, null)

    @Provides
    fun provideNoteDao(appDatabase: AppDatabase): NoteDao = appDatabase.noteDao

}
