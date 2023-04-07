package com.example.android.training.presenter.ui.product.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ProductLayout(
    @Json(name = "brand") val brand: String,
    @Json(name = "category") val category: String,
    @Json(name = "description") val description: String,
    @Json(name = "discountPercentage") val discountPercentage: Double,
    @Json(name = "id") val id: Int,
    @Json(name = "images") val images: List<String>,
    @Json(name = "price") val price: Int,
    @Json(name = "rating") val rating: Double,
    @Json(name = "stock") val stock: Int,
    @Json(name = "thumbnail") val thumbnail: String,
    @Json(name = "title") val title: String
) : Parcelable