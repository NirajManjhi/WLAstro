package com.wl.astro.di.modules

import androidx.lifecycle.ViewModelProvider
import com.wl.astro.di.ViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * Created by NirajM on 22/06/21.
 * Version 1.0
 */
@Module
abstract class ViewModelFactoryModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}