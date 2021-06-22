package com.wl.astro.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by NirajM on 22/06/21.
 * Version 1.0
 */
open class SchedulersFacade @Inject constructor() {
    /**
     * IO thread pool scheduler
     */
    open fun io(): Scheduler = Schedulers.io()

    /**
     * Computation thread pool scheduler
     */
    open fun computation(): Scheduler = Schedulers.computation()

    /**
     * Main Thread scheduler
     */
    open fun ui(): Scheduler = AndroidSchedulers.mainThread()
}