package com.example.android.training.presenter.ui.home.presenter.application

import android.app.Application
import com.example.android.training.MyPreference
import com.example.android.training.data.ProductDetailDatabase
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class ProductApplication : Application() {
    @Inject
    lateinit var myPreference: MyPreference

    val database: ProductDetailDatabase by lazy { ProductDetailDatabase.getDatabaseInstance(this) }
}