package com.example.android.training.presenter.ui.product.controller

import android.util.Log
import com.airbnb.epoxy.TypedEpoxyController
import com.example.android.training.presenter.ui.product.controller.model.ClickProduct
import com.example.android.training.presenter.ui.product.controller.model.product
import com.example.android.training.presenter.ui.product.model.ProductViewState

class ProductController(
    private val itemClick: ClickProduct
) : TypedEpoxyController<ProductViewState>() {
    override fun buildModels(data: ProductViewState?) {
        if (data is ProductViewState.Success) {
            data.productDataModel?.forEach { data ->
                product {
                    id("product")
                    product(data)
                    itemClick(this@ProductController.itemClick)
                }
            }
        } else {
            repeat(10) {
                product {
                    id("product$it")
                    product(null)
                    itemClick(this@ProductController.itemClick)
                }
            }
        }
    }
}