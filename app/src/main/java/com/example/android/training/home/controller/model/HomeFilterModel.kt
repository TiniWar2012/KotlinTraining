package com.example.android.training.home.controller.model

import android.annotation.SuppressLint
import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.EpoxyModelClass
import com.example.android.training.R
import com.example.android.training.base.EpoxyViewBindingModelWithHolder
import com.example.android.training.databinding.LayoutFilterBinding
import com.example.android.training.databinding.LayoutHeaderBannerBinding
import com.example.android.training.home.adapter.FilterAdapter

@EpoxyModelClass(layout = R.layout.layout_filter)
abstract class HomeFilterModel : EpoxyViewBindingModelWithHolder<LayoutFilterBinding>() {

    @SuppressLint("SuspiciousIndentation")
    override fun LayoutFilterBinding.bind(context: Context) {
        val filterAdapter = FilterAdapter()
        filterAdapter.setData()
        filterRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        filterRecyclerView.apply {
            adapter = filterAdapter
        }

    }
}