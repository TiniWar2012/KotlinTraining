package com.example.android.training.home.presenter.application

import android.app.Application
import com.example.android.training.home.presenter.data.ProductDetailDatabase

class ProductApplication : Application() {
    val database : ProductDetailDatabase by lazy { ProductDetailDatabase.getDatabasenIstance(this) }
}