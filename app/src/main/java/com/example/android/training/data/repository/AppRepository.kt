package com.example.android.training.data.repository

import com.example.android.training.data.service.BannerService
import com.example.android.training.data.service.ProductNewService
import com.example.android.training.data.service.ProductService
import com.example.android.training.presenter.ui.home.model.HomeBannerLayout
import com.example.android.training.presenter.ui.home.model.HomeProductLayout
import com.example.android.training.presenter.ui.product.model.ProductLayout
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val bannerService: BannerService,
    private val productService: ProductService,
    private val productNewService: ProductNewService
) {

    fun getBanner(): Single<List<HomeBannerLayout>> {
        return bannerService.getBanner()
    }

    fun getProductList(): Single<List<HomeProductLayout>>{
        return  productService.getProduct()
    }

    fun getNewProductList(): Single<List<ProductLayout>>{
        return productNewService.getProduct().map { it.products }
    }
}