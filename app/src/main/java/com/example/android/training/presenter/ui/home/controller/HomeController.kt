package com.example.android.training.presenter.ui.home.controller

import com.airbnb.epoxy.TypedEpoxyController
import com.example.android.training.presenter.ui.home.adapter.HandleClickListener
import com.example.android.training.presenter.ui.home.adapter.ItemClickListener
import com.example.android.training.presenter.ui.home.controller.model.bestSeller
import com.example.android.training.presenter.ui.home.controller.model.headerBanner
import com.example.android.training.presenter.ui.home.controller.model.homeFilter
import com.example.android.training.presenter.ui.home.controller.model.newArrivalProduct
import com.example.android.training.presenter.ui.home.model.HomeViewState

class HomeController(
    private val onClick: HandleClickListener,
    private val itemClick: ItemClickListener
) :
    TypedEpoxyController<HomeViewState>() {
    override fun buildModels(data: HomeViewState?) {
        data?.run {
            homeFilter {
                id("_home_filter")
            }
            headerBanner {
                id("_header_image_banner")
                listBanner(homeBannerDataModel)
            }
            newArrivalProduct {
                id("new_arrival")
                listProduct(homeProductDataModel)
                onClick(this@HomeController.onClick)
                itemClick(this@HomeController.itemClick)
            }
            bestSeller {
                id("best_seller")
                listProduct(homeProductDataModel)
                onClick(this@HomeController.onClick)
                itemClick(this@HomeController.itemClick)
            }
        }
    }
}