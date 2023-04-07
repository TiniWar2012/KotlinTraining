package com.example.android.training.presenter.ui.home.model

import android.util.Log
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HomeBannerLayout(
    @Json(name = "id") val id: String,
    @Json(name = "url") val url: String,
    @Json(name = "description") val description: String
) {
    fun getImageUrl(): String {
        Log.d("--->", url)
        return url
    }
}