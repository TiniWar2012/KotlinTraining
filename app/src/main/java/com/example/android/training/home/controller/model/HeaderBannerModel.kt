package com.example.android.training.home.controller.model

import android.content.Context
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.example.android.training.R
import com.example.android.training.base.EpoxyViewBindingHolder
import com.example.android.training.base.EpoxyViewBindingModelWithHolder
import com.example.android.training.databinding.LayoutHeaderBannerBinding
import com.example.android.training.home.adapter.HeaderBannerAdapter
import com.example.android.training.home.model.HomeBannerLayout

@EpoxyModelClass(layout = R.layout.layout_header_banner)
abstract class HeaderBannerModel : EpoxyViewBindingModelWithHolder<LayoutHeaderBannerBinding>() {
    @EpoxyAttribute
    lateinit var listBanner: List<HomeBannerLayout>

    lateinit var callback: OnPageChangeCallback

    override fun LayoutHeaderBannerBinding.bind(context: Context) {
        val headerBannerAdapter = HeaderBannerAdapter()
        val runnable = Runnable {
            val totalItem: Int = listBanner.size - 1
            val currentItem: Int = viewPager.currentItem
            if (currentItem < totalItem) {
                viewPager.currentItem = viewPager.currentItem + 1
            } else {
                viewPager.currentItem = 0
            }
        }

        if (::listBanner.isInitialized) {
            headerBannerAdapter.setData(listBanner)
            viewPager.apply {
                adapter = headerBannerAdapter
                registerOnPageChangeCallback(object : OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        handler.removeCallbacks(runnable)
                        handler.postDelayed(runnable, 4000)
                    }
                }.also { callback = it })
            }
            tabDots.setViewPager(viewPager)
            headerBannerAdapter.registerAdapterDataObserver(tabDots.adapterDataObserver)
        }
    }

    override fun onViewDetachedFromWindow(holder: EpoxyViewBindingHolder) {
        if (::callback.isInitialized) {
            (holder.viewBinding as LayoutHeaderBannerBinding)
                .viewPager
                .unregisterOnPageChangeCallback(callback)
        }
        super.onViewDetachedFromWindow(holder)
    }
}