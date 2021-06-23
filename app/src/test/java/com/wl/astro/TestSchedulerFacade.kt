package com.wl.astro

import com.wl.astro.rx.SchedulersFacade
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Created by NirajM on 23/06/21.
 * Version 1.0
 */
class TestSchedulerFacade : SchedulersFacade() {
    override fun io(): Scheduler = Schedulers.trampoline()

    /**
     * Computation thread pool scheduler
     */
    override fun computation(): Scheduler = Schedulers.trampoline()

    /**
     * Main Thread scheduler
     */
    override fun ui(): Scheduler = Schedulers.trampoline()
}