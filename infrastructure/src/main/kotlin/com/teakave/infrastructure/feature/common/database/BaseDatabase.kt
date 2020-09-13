package com.teakave.infrastructure.feature.common.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteOpenHelper

abstract class BaseDatabase : RoomDatabase() {

    companion object {
        /**
         * Creates instance of this DB.
         *
         * @param context Context of application
         * @param openHelperFactory SupportOpenHelper factory
         * @return Instance of this DB
         */
        inline fun <reified T : BaseDatabase> buildDatabase(
            context: Context,
            databaseName: String,
            openHelperFactory: SupportSQLiteOpenHelper.Factory? = null,
            migration: Array<Migration>? = null
        ): T {

            return Room.databaseBuilder(
                context.applicationContext,
                T::class.java, databaseName
            )
                .openHelperFactory(openHelperFactory)
                .fallbackToDestructiveMigration()
                .apply {
                    migration?.let {
                        addMigrations(*it)
                    }
                }
                .build()
        }
    }

}
