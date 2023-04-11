package com.example.android.training.data.di

import com.example.android.training.data.service.BannerService
import com.example.android.training.data.service.ProductNewService
import com.example.android.training.data.service.ProductService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Singleton
    @Provides
    fun provideRxJava3CallAdapterFactory(): RxJava3CallAdapterFactory {
        return RxJava3CallAdapterFactory.create()
    }

    @Singleton
    @Provides
    fun provideMoshi(): MoshiConverterFactory {
        return MoshiConverterFactory.create(Moshi.Builder().add(KotlinJsonAdapterFactory()).build())
    }

    @Singleton
    @Provides
    @Named("BannerRetrofit")
    fun provideBannerRetrofit(
        okHttpClient: OkHttpClient,
        rxJava3CallAdapterFactory: RxJava3CallAdapterFactory,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://63ad45b2da81ba9761977557.mockapi.io/central/v1/")
            .addCallAdapterFactory(rxJava3CallAdapterFactory)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    @Named("ProductRetrofit")
    fun provideProductRetrofit(
        okHttpClient: OkHttpClient,
        rxJava3CallAdapterFactory: RxJava3CallAdapterFactory,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://fakestoreapi.com/")
//            .baseUrl("https://api.escuelajs.co/api/v1/")
            .addCallAdapterFactory(rxJava3CallAdapterFactory)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }
    @Singleton
    @Provides
    @Named("ProductNewRetrofit")
    fun provideProductRetrofitNew(
        okHttpClient: OkHttpClient,
        rxJava3CallAdapterFactory: RxJava3CallAdapterFactory,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://dummyjson.com/")
            .addCallAdapterFactory(rxJava3CallAdapterFactory)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideBannerService(
        @Named("BannerRetrofit") retrofit: Retrofit
    ): BannerService {
        return retrofit.create(BannerService::class.java)
    }

    @Singleton
    @Provides
    fun provideProductService(
        @Named("ProductRetrofit") retrofit: Retrofit
    ): ProductService {
        return retrofit.create(ProductService::class.java)
    }

    @Singleton
    @Provides
    fun provideProductNewService(
        @Named("ProductNewRetrofit") retrofit: Retrofit
    ): ProductNewService {
        return retrofit.create(ProductNewService::class.java)
    }
}