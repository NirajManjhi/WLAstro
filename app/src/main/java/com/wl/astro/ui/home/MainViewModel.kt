package com.wl.astro.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wl.astro.common.EXTRA_IS_LOADING
import com.wl.astro.common.EXTRA_MSG
import com.wl.astro.rx.SchedulersFacade
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by NirajM on 23/06/21.
 * Version 1.0
 */
class MainViewModel @Inject constructor(private val scheduler: SchedulersFacade) : ViewModel() {
    companion object {
        const val TAG = "MainViewModel"
    }

    private var compositeDisposable = CompositeDisposable()
    private val loading = MutableLiveData<Boolean>()
    private val messageSnackBar = MutableLiveData<String>()

    fun getLoading(): LiveData<Boolean> = loading

    fun getMsgSnackBar(): LiveData<String> = messageSnackBar

    fun subscribeToFragmentEvents() {
        compositeDisposable.add(HomeBus.get().events.observeOn(scheduler.ui())
            .subscribe({ events -> filterEvents(events) }, { e -> Log.e(TAG, e.message.orEmpty()) })
        )
    }

    private fun filterEvents(events: HomeBus.HomeEvents) {
        when (events.event) {
            HomeBus.SHOW_HIDE_LOADING -> loading.value = events.args?.getBoolean(EXTRA_IS_LOADING) ?: false
            HomeBus.SHOW_SNACKBAR -> messageSnackBar.value = events.args?.getString(EXTRA_MSG, "")
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}