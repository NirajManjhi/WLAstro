package com.wl.astro.di.modules

import androidx.lifecycle.ViewModel
import com.wl.astro.di.ViewModelKey
import com.wl.astro.ui.home.HomeViewModel
import com.wl.astro.ui.home.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by NirajM on 22/06/21.
 * Version 1.0
 */
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}