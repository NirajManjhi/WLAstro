package com.wl.astro.di.modules

import com.wl.astro.ui.home.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by NirajM on 22/06/21.
 * Version 1.0
 */
@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [MainFragmentBindingModule::class, ViewModelModule::class])
    abstract fun bindMainActivity(): MainActivity
}