package com.example.android.training.home.controller.model

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.example.android.training.R
import com.example.android.training.base.EpoxyViewBindingModelWithHolder
import com.example.android.training.databinding.LayoutProductRecyclerviewBinding
import com.example.android.training.home.adapter.HandleClickListener
import com.example.android.training.home.adapter.ProductAdapter
import com.example.android.training.home.model.HomeProductLayout

@EpoxyModelClass(layout = R.layout.layout_product_recyclerview)

abstract class BestSellerModel  :
    EpoxyViewBindingModelWithHolder<LayoutProductRecyclerviewBinding>() {
    @EpoxyAttribute
    lateinit var listProduct: List<HomeProductLayout>

    @EpoxyAttribute
    lateinit var onClick: HandleClickListener

    override fun LayoutProductRecyclerviewBinding.bind(context: Context) {
        val productAdapter = ProductAdapter(onClick)
        if (::listProduct.isInitialized) {
            productAdapter.setData(listProduct)
        }
        recyclerView.apply {
            adapter = productAdapter
            productListType.text = "BEST SELLER"
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        }
    }
}