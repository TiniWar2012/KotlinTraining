package com.example.android.training.home.model

data class HomeViewState(
    var homeProductDataModel : List<HomeProductLayout>? = null,
    var homeBannerDataModel : List<HomeBannerLayout>? = null,
    var homeFilterDataModel : List<HomeFilterLayout>? = null,
)
