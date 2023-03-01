package com.example.android.training.home.presenter

interface HomeView {

    fun showLoading()
    fun hideLoading()

    fun showEmpty()
    fun showProducts(products: List<String>?)
}