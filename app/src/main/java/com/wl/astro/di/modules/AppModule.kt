package com.wl.astro.di.modules

import androidx.room.Room
import com.wl.astro.App
import com.wl.astro.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by NirajM on 22/06/21.
 * Version 1.0
 */
@Module
internal class AppModule {
    @Singleton
    @Provides
    fun provideAppDatabase(app: App): AppDatabase =
        Room.databaseBuilder(app.applicationContext, AppDatabase::class.java, AppDatabase.DATABASE_NAME).build()
}