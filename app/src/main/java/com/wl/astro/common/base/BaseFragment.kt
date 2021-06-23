package com.wl.astro.common.base

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import dagger.android.support.DaggerFragment

/**
 * Created by NirajM on 23/06/21.
 * Version 1.0
 */
open class BaseFragment : DaggerFragment() {
    internal fun isNetworkConnected(cm: ConnectivityManager): Boolean {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            val activeNetwork = cm.activeNetwork ?: return false
            val capabilities = cm.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            cm.activeNetworkInfo?.run {
                return when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }
}