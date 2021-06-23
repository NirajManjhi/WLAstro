package com.wl.astro.ui.home

import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.wl.astro.R
import com.wl.astro.common.EXTRA_MSG
import com.wl.astro.common.MEDIA_TYPE_VIDEO
import com.wl.astro.common.base.BaseFragment
import com.wl.astro.databinding.FragmentHomeBinding
import javax.inject.Inject

/**
 * Created by NirajM on 22/06/21.
 * Version 1.0
 */
class HomeFragment : BaseFragment() {
    private var binding: FragmentHomeBinding? = null

    @Inject
    lateinit var cm: ConnectivityManager

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchData()
        viewModel.getError().observe(viewLifecycleOwner, { resId ->
            binding?.containerErr?.visibility = View.VISIBLE
            binding?.txvErr?.text = getString(resId)
        })

        viewModel.getMessage().observe(viewLifecycleOwner, {
            HomeBus.get().post(HomeBus.HomeEvents(event = HomeBus.SHOW_SNACKBAR, Bundle().apply {
                putString(
                    EXTRA_MSG, getString(it)
                )
            }))
        })

        binding?.btnRetry?.setOnClickListener {
            binding?.containerErr?.visibility = View.GONE
            fetchData()
        }
    }

    private fun fetchData() {
        viewModel.fetchAstroFromLocal(isNetworkConnected(cm)).observe(viewLifecycleOwner, { astroResponse ->
            binding?.containerMain?.visibility = View.VISIBLE
            binding?.imvAstro?.let {
                if (astroResponse.mediaType == MEDIA_TYPE_VIDEO) {
                    it.setImageResource(R.drawable.bg_play)
                    it.setOnClickListener {
                        val uri = Uri.parse(astroResponse.url)
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        startActivity(intent)
                    }
                } else {
                    Glide.with(it).load(astroResponse.url).into(it)
                }
            }
            binding?.txvTitle?.text = astroResponse.title
            binding?.txvDesc?.text = astroResponse.desc
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}