package com.wl.astro.common.base

import com.google.android.material.snackbar.Snackbar
import com.wl.astro.R
import dagger.android.support.DaggerAppCompatActivity

/**
 * Created by NirajM on 23/06/21.
 * Version 1.0
 */
open class BaseActivity : DaggerAppCompatActivity() {
    internal fun showLongSnackBar(message: String) {
        val snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction(getString(R.string.btn_ok)) { snackbar.dismiss() }
        snackbar.show()
    }
}