package com.example.android.training.interface2324

import com.example.android.training.home.model.HomeProductLayout
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL="https://fakestoreapi.com"
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL).build()

interface product_interface {
    @GET("/products")
    fun getAllData():  Call<List<HomeProductLayout>>
}
object Api {
    val retrofitService: product_interface by lazy{retrofit.create(product_interface::class.java)}
}