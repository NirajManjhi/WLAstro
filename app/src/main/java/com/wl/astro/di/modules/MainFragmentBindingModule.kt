package com.wl.astro.di.modules

import com.wl.astro.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by NirajM on 22/06/21.
 * Version 1.0
 */
@Module
internal abstract class MainFragmentBindingModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment
}