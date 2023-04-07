package com.example.android.training.data.service

import com.example.android.training.presenter.ui.home.model.HomeProductLayout
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ProductService {
    @GET("products/")
    fun getProduct(): Single<List<HomeProductLayout>>
}