package com.wl.astro.di

import com.wl.astro.App
import com.wl.astro.di.modules.ActivityBuilderModule
import com.wl.astro.di.modules.AppModule
import com.wl.astro.di.modules.NetModule
import com.wl.astro.di.modules.RepoModule
import com.wl.astro.di.modules.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by NirajM on 22/06/21.
 * Version 1.0
 */
@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, ActivityBuilderModule::class, NetModule::class, ViewModelFactoryModule::class, RepoModule::class, AppModule::class]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: App): AppComponent
    }
}