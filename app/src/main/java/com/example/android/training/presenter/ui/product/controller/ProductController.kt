package com.example.android.training.presenter.ui.product.controller

import com.airbnb.epoxy.TypedEpoxyController
import com.example.android.training.presenter.ui.product.controller.model.ProductModel
import com.example.android.training.presenter.ui.product.controller.model.product
import com.example.android.training.presenter.ui.product.model.ProductViewState

class ProductController(): TypedEpoxyController<ProductViewState>() {
    override fun buildModels(data: ProductViewState?) {
        data?.run {
//            productDataModel {
//                id("product")
//                lisProduct(productDataModel)
//            }
            product {
                id("product")
                listProduct(productDataModel)
            }
        }
    }
}