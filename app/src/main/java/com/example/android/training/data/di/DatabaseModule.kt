package com.example.android.training.data.di

import android.app.Application
import com.example.android.training.presenter.ui.home.presenter.application.ProductApplication
import com.example.android.training.presenter.ui.home.presenter.dao.ProductDataDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDao(application: Application): ProductDataDao {
        return (application as ProductApplication).database.productDataDao()
    }
}