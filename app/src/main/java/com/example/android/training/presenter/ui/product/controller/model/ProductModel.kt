package com.example.android.training.presenter.ui.product.controller.model

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.example.android.training.R
import com.example.android.training.base.EpoxyViewBindingModelWithHolder
import com.example.android.training.databinding.LayoutProductVerticalBinding
import com.example.android.training.presenter.ui.product.adapter.ProductAdapter
import com.example.android.training.presenter.ui.product.model.ProductLayout

@EpoxyModelClass(layout = R.layout.layout_product_vertical)
abstract class ProductModel : EpoxyViewBindingModelWithHolder<LayoutProductVerticalBinding>() {

    @EpoxyAttribute
    lateinit var listProduct: List<ProductLayout>

    override fun LayoutProductVerticalBinding.bind(context: Context) {
        val productAdapter = ProductAdapter()
        if (::listProduct.isInitialized) {
            productAdapter.setData(listProduct)
        }
        recyclerView.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        }
    }
}