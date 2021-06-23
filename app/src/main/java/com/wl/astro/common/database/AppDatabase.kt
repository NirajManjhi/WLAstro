package com.wl.astro.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wl.astro.common.database.AppDatabase.Companion.DATABASE_VERSION

/**
 * Created by NirajM on 22/06/21.
 * Version 1.0
 */
@Database(entities = [AstroTable::class], version = DATABASE_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    // dao interfaces
    abstract val astroDao: AstroDao

    companion object {
        const val DATABASE_NAME = "wl_astro"
        const val DATABASE_VERSION = 1

        //table names
        const val TABLE_ASTRO = "astroTable"
    }
}