package com.wl.astro

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by NirajM on 22/06/21.
 * Version 1.0
 */
interface ApiService {
    @GET("apod")
    fun getAstroForToday(@Query("api_key") apiKey: String): Single<AstroResponse?>
}