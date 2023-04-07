package com.example.android.training.presenter.ui.home.controller.model

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.example.android.training.R
import com.example.android.training.base.EpoxyViewBindingModelWithHolder
import com.example.android.training.databinding.LayoutProductRecyclerviewBinding
import com.example.android.training.presenter.ui.home.adapter.HandleClickListener
import com.example.android.training.presenter.ui.home.adapter.ItemClickListener
import com.example.android.training.presenter.ui.home.adapter.ProductAdapter
import com.example.android.training.presenter.ui.home.model.HomeProductLayout

@EpoxyModelClass(layout = R.layout.layout_product_recyclerview)
abstract class NewArrivalProductModel :
    EpoxyViewBindingModelWithHolder<LayoutProductRecyclerviewBinding>() {
    @EpoxyAttribute
    lateinit var listProduct: List<HomeProductLayout>

    @EpoxyAttribute
    lateinit var onClick: HandleClickListener

    @EpoxyAttribute
    lateinit var itemClick: ItemClickListener

    override fun LayoutProductRecyclerviewBinding.bind(context: Context) {
        val productAdapter = ProductAdapter(
            onClick = onClick,
            itemClick = itemClick
        )
        if (::listProduct.isInitialized) {
            productAdapter.setData(listProduct)
        }
        recyclerView.apply {
            adapter = productAdapter
            productListType.text = context.getString(R.string.NEW_ARRIVALS)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        }
    }
}