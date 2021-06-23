package com.wl.astro.ui.home

import android.os.Bundle
import com.wl.astro.rx.RxBus

/**
 * Created by NirajM on 22/06/21.
 * Version 1.0
 */
class HomeBus private constructor() : RxBus<HomeBus.HomeEvents>() {
    companion object {
        const val SHOW_HIDE_LOADING = "SHOW_HIDE_LOADING"
        const val SHOW_SNACKBAR = "SHOW_SNACKBAR"
        private lateinit var INSTANCE: HomeBus

        @Synchronized
        fun get(): HomeBus {
            if (!::INSTANCE.isInitialized) {
                INSTANCE = HomeBus()
            }
            return INSTANCE
        }
    }

    data class HomeEvents(val event: String, val args: Bundle? = null)
}