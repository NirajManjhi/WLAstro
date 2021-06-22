package com.wl.astro.rx

import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable

/**
 * Created by NirajM on 22/06/21.
 * Version 1.0
 */
abstract class RxBus<T> {
    private val rxBus = PublishRelay.create<T>().toSerialized()

    fun post(event: T) = rxBus.accept(event)

    val events: Flowable<T>
        get() = rxBus.toFlowable(BackpressureStrategy.DROP)
}