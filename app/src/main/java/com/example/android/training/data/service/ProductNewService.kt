package com.example.android.training.data.service

import com.example.android.training.presenter.ui.product.model.ProductLayout
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ProductNewService {
    @GET("products/")
    fun getProduct(): Single<List<ProductLayout>>
}