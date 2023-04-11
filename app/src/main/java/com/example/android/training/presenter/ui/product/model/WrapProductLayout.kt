package com.example.android.training.presenter.ui.product.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WrapProductLayout(
    @Json(name = "products")
    val products: List<ProductLayout>
)