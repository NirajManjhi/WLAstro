package com.wl.astro.ui.home

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wl.astro.R
import com.wl.astro.common.DATE_FORMAT_YYYY_MM_DD
import com.wl.astro.common.EXTRA_IS_LOADING
import com.wl.astro.common.extensions.transformToModel
import com.wl.astro.common.extensions.transformToTable
import com.wl.astro.common.model.HomeModel
import com.wl.astro.repo.HomeRepo
import com.wl.astro.rx.SchedulersFacade
import io.reactivex.disposables.CompositeDisposable
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by NirajM on 22/06/21.
 * Version 1.0
 */
class HomeViewModel @Inject constructor(private val repository: HomeRepo, private val scheduler: SchedulersFacade) :
    ViewModel() {
    private var compositeDisposable = CompositeDisposable()
    private val astroData = MutableLiveData<HomeModel>()
    private val error = MutableLiveData<Int>()
    private val message = MutableLiveData<Int>()

    companion object {
        const val TAG = "HomeViewModel"
    }

    fun fetchAstroFromLocal(networkConnected: Boolean): LiveData<HomeModel> {
        val d = repository.getLocalAstroForToday().map {
            it.firstOrNull()?.transformToModel()
        }.subscribeOn(scheduler.io()).observeOn(scheduler.ui()).subscribe({
            if (it == null) {
                fetchAstroFromServer(networkConnected = networkConnected)
            } else {
                val dateTimeFormat = SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD, Locale.getDefault())
                val today = dateTimeFormat.format(Date())
                if (it.date == today) {
                    astroData.value = it
                } else {
                    fetchAstroFromServer(networkConnected = networkConnected, oldData = it)
                }
            }
        }, {
            fetchAstroFromServer(networkConnected = networkConnected)
        })
        compositeDisposable.add(d)
        return astroData
    }

    private fun fetchAstroFromServer(networkConnected: Boolean, oldData: HomeModel? = null) {
        if (!networkConnected) {
            oldData?.let {
                message.value = R.string.msg_showing_last_image
                astroData.value = it
            } ?: run {
                error.value = R.string.err_internet
            }
            return
        }
        val d = repository.getAstroData().map {
            it.transformToModel()
        }.subscribeOn(scheduler.io()).observeOn(scheduler.ui()).doOnSubscribe {
            HomeBus.get().post(HomeBus.HomeEvents(HomeBus.SHOW_HIDE_LOADING, Bundle().apply {
                putBoolean(
                    EXTRA_IS_LOADING, true
                )
            }))
        }.doOnError {
            HomeBus.get().post(HomeBus.HomeEvents(HomeBus.SHOW_HIDE_LOADING))
        }.doOnSuccess {
            HomeBus.get().post(HomeBus.HomeEvents(HomeBus.SHOW_HIDE_LOADING))
        }.subscribe({
            if (it != null) {
                insertDataToLocal(it)
                astroData.value = it
            }
        }, { Log.d(TAG, it.message.orEmpty()) })
        compositeDisposable.add(d)
    }

    private fun insertDataToLocal(homeModel: HomeModel) {
        val d = repository.insertAstroDataToLocal(homeModel.transformToTable()).subscribeOn(scheduler.io())
            .observeOn(scheduler.ui()).subscribe({ // do nothing
            }, {
                Log.e(TAG, it.message.orEmpty())
            })
        compositeDisposable.add(d)
    }

    fun getError(): LiveData<Int> = error

    fun getMessage(): LiveData<Int> = message

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}