package com.example.android.training.home.presenter.service

import com.example.android.training.home.model.HomeBannerLayout
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://63ad45b2da81ba9761977557.mockapi.io/central/v1/"
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder().addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL).build()

interface BannerService {
    @GET("banner/")
    fun getBanner(): Single<List<HomeBannerLayout>>
}

object bannerApi {
    val retrofitService: BannerService by lazy { retrofit.create(BannerService::class.java) }
}