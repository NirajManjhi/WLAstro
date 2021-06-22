package com.wl.astro.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wl.astro.AstroResponse
import com.wl.astro.repo.HomeRepo
import com.wl.astro.rx.SchedulersFacade
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by NirajM on 22/06/21.
 * Version 1.0
 */
class HomeViewModel @Inject constructor(private val repository: HomeRepo, private val scheduler: SchedulersFacade) :
    ViewModel() {
    private var compositeDisposable = CompositeDisposable()
    private val astroData = MutableLiveData<AstroResponse>()

    init {
        fetchAstro()
    }

    private fun fetchAstro() {
        val d = repository.getAstroData().subscribeOn(scheduler.io()).observeOn(scheduler.ui()).subscribe({
            if (it != null) {
                astroData.value = it
            }
        }, {})
        compositeDisposable.add(d)
    }

    fun getAstroData(): LiveData<AstroResponse> = astroData

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}