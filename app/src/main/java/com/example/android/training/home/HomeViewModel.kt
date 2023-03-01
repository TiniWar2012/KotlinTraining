package com.example.android.training.home

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.training.home.adapter.FilterAdapter
import com.example.android.training.home.controller.model.HeaderBannerModel
import com.example.android.training.home.model.*
import com.example.android.training.home.presenter.`interface`.banner_api
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel : ViewModel() {
    private val homeBannerLiveData by lazy { MutableLiveData<List<HomeBannerLayout>>() }
    private val homeProductLiveData by lazy { MutableLiveData<List<HomeProductLayout>>() }
    private val homeFilterLiveData by lazy { MutableLiveData<List<HomeFilterLayout>>() }
    private val compositeDisposable = CompositeDisposable()

    val homeViewStateLiveData: MediatorLiveData<HomeViewState> by lazy {
        MediatorLiveData<HomeViewState>().apply {
            addSource(homeBannerLiveData) { source ->
                homeViewStateLiveData.value = homeViewStateLiveData.value?.copy(
                    homeBannerDataModel = source
                ) ?: HomeViewState(
                    homeBannerDataModel = source
                )
            }
            addSource(homeProductLiveData) { source ->
                homeViewStateLiveData.value = homeViewStateLiveData.value?.copy(
                    homeProductDataModel = source
                ) ?: HomeViewState(
                    homeProductDataModel = source
                )
            }
            addSource(homeFilterLiveData) { source ->
                homeViewStateLiveData.value = homeViewStateLiveData.value?.copy(
                    homeFilterDataModel = source
                ) ?: HomeViewState(
                    homeFilterDataModel = source
                )
            }
        }
    }

    init {
        getObserverHeaderBanner()
//        getObserverHomeBanner()
    }

    private fun getObserverHeaderBanner() {
        compositeDisposable.add(banner_api.retrofitService.getBanner().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                // on success
                homeBannerLiveData.value = it
            }, {
                // on error
                Log.d("--->", it.message.orEmpty())
            })
        )
    }

//    private fun getObserverHomeBanner() {
//        compositeDisposable.add(
//            Single.just(listOf())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                    {
//                        homeFilterLiveData.value = it
//                    }, {
//                        Log.d("--->", it.message.orEmpty())
//                    }
//                )
//        )
//    }

}