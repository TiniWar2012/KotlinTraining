package com.example.android.training.presenter.ui.product.model

import android.os.Parcelable
import com.example.android.training.presenter.ui.home.model.Category
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class HomeProductLayoutNew(
    @Json(name = "category") val category: Category,
    @Json(name = "creationAt")  val creationAt: String,
    @Json(name = "description")  val description: String,
    @Json(name = "id")  val id: Int,
    @Json(name = "images")  val images: List<String>,
    @Json(name = "price")  val price: String,
    @Json(name = "title")  val title: String,
    @Json(name = "updatedAt")  val updatedAt: String,
    var like: Boolean = false
): Parcelable