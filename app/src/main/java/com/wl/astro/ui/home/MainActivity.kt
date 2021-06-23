package com.wl.astro.ui.home

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.wl.astro.common.base.BaseActivity
import com.wl.astro.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : BaseActivity() {
    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.subscribeToFragmentEvents()
        viewModel.getLoading().observe(this, {
            showHideLoader(it)
        })
        viewModel.getMsgSnackBar().observe(this, {
            if (!it.isNullOrBlank()) {
                showLongSnackBar(it)
            }
        })
    }

    private fun showHideLoader(show: Boolean) {
        if (show) {
            binding.progress.visibility = View.VISIBLE
            window?.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
        } else {
            binding.progress.visibility = View.GONE
            window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }
    }
}