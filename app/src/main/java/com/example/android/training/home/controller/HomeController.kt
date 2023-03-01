package com.example.android.training.home.controller

import com.airbnb.epoxy.TypedEpoxyController
import com.example.android.training.home.controller.model.HeaderBannerModel
import com.example.android.training.home.controller.model.headerBanner
import com.example.android.training.home.controller.model.homeFilter
import com.example.android.training.home.model.HomeBannerLayout
import com.example.android.training.home.model.HomeViewState

class HomeController : TypedEpoxyController<HomeViewState>() {
    override fun buildModels(data: HomeViewState?) {
        data?.run {
            headerBanner {
                id("_header_image_banner")
                listBanner(homeBannerDataModel)
            }
            homeFilter {
                id("_home_filter")
            }
        }
    }

//    private fun HomeViewState.homeBanner() {
//        homeBannerDataModel?.let{
//            headerBanner {
//                id("_header_image_banner")
//                listBanner(it)
//            }
//        }
//    }
//    private fun HomeViewState.homeFilter() {
//        homeFilter(){
//            id("home_filter")
//        }
//    }
//    private fun HomeViewState.homeProduct() {
//        TODO("Not yet implemented")
//    }

}