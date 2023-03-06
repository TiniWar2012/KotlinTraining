package com.example.android.training.home.presenter.service

import com.example.android.training.home.model.Product
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL="https://fakestoreapi.com"
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(
    BASE_URL
).build()
interface product2_interface {
        @GET("/products/")
        fun getAllProducts(): Call<List<Product>>
}
object product_gridview_api {
    val retrofitService: product2_interface by lazy{ retrofit.create(product2_interface::class.java)}
}