package com.example.android.training.presenter.ui.home.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Category(
    @Json(name = "creationAt") val creationAt: String,
    @Json(name = "id")  val id: Int,
    @Json(name = "image")  val image: String,
    @Json(name = "name")  val name: String,
    @Json(name = "updatedAt")  val updatedAt: String
): Parcelable