package com.wl.astro.repo

import com.wl.astro.ApiService
import com.wl.astro.AppDatabase
import com.wl.astro.AstroResponse
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by NirajM on 22/06/21.
 * Version 1.0
 */
class HomeRepo @Inject constructor(private val apiService: ApiService, private val appDatabase: AppDatabase) {
    fun getAstroData(): Single<AstroResponse?> {
        return apiService.getAstroForToday(apiKey = "wyrlKPkI6AgTt083hbPGacYNRJKbuQfFh1ZmHMd7")
    }
}