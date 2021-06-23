package com.wl.astro.di.modules

import com.wl.astro.BuildConfig
import com.wl.astro.common.ApiService
import com.wl.astro.common.BASE_URL
import com.wl.astro.rx.SchedulersFacade
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by NirajM on 22/06/21.
 * Version 1.0
 */
@Module
internal class NetModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofitApiService(okHttpClient: OkHttpClient): ApiService =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(okHttpClient).build()
            .create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideSchedulerFacade() = SchedulersFacade()
}