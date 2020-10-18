package com.teakave.noteapp

import android.app.Application
import com.teakave.noteapp.app.core.di.noteAppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class NoteApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NoteApplication)
            modules(noteAppModule)
        }
    }

}
