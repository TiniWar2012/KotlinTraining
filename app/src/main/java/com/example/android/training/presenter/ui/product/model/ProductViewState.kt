package com.example.android.training.presenter.ui.product.model

//data class ProductViewState1(
//
//
//    var productDataModel: List<ProductLayout>? = null,
//)

sealed class ProductViewState {
    class Success(
        val productDataModel: List<ProductLayout>? = null
    ) : ProductViewState()

    object Loading: ProductViewState()
}
