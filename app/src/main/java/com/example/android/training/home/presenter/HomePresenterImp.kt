package com.example.android.training.home.presenter

class HomePresenterImp(private val view: HomeView) : HomePresenter {




    override fun loadProducts() {
        view.showLoading()
        val data = arrayListOf("Product 1", "Product 2")
        view.hideLoading()
        view.showProducts(data)
    }

    override fun loadProductById(id: String) {

    }
}
