package com.example.android.training.data.service

import com.example.android.training.presenter.ui.home.model.HomeBannerLayout
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface BannerService {
    @GET("banner/")
    fun getBanner(): Single<List<HomeBannerLayout>>
}