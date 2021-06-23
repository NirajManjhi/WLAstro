package com.wl.astro.di.modules

import com.wl.astro.common.ApiService
import com.wl.astro.common.database.AppDatabase
import com.wl.astro.repo.HomeRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by NirajM on 22/06/21.
 * Version 1.0
 */
@Module
internal class RepoModule {
    @Singleton
    @Provides
    fun provideHomeRepo(apiService: ApiService, appDatabase: AppDatabase): HomeRepo {
        return HomeRepo(apiService, appDatabase)
    }
}