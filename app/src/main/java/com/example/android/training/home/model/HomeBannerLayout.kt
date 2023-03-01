package com.example.android.training.home.model

import android.util.Log

data class HomeBannerLayout(
    val id: String,
    val url: String,
    val description: String
){
    fun getImageUrl(): String? {
        Log.d("--->", url)
        return url
    }
}