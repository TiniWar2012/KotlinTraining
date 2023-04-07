package com.example.android.training.presenter.di

import android.app.Application
import android.content.Context
import com.example.android.training.MyPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideMyPreference(@ApplicationContext context: Context): MyPreference {
        return MyPreference(
            context.getSharedPreferences(
                MyPreference.PREFERENCE_NAME,
                Context.MODE_PRIVATE
            )
        )
    }
}