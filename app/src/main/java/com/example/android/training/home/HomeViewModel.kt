package com.example.android.training.home

import android.util.Log
import androidx.lifecycle.*
//import com.example.android.training.home.controller.model.HeaderBannerModel
import com.example.android.training.home.model.*
import com.example.android.training.home.presenter.dao.ProductDataDao
import com.example.android.training.home.presenter.data.ProductDetailDatabase
import com.example.android.training.home.presenter.service.bannerApi
import com.example.android.training.interface2324.productApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val productDao: ProductDataDao) : ViewModel() {
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
        getObserverHomeProduct()
    }

    private fun getObserverHeaderBanner() {
        compositeDisposable.add(
            bannerApi.retrofitService.getBanner().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    // on success
                    homeBannerLiveData.value = it
                }, {
                    // on error
                    Log.d("--->", it.message.orEmpty())
                })
        )
    }

    private fun getObserverHomeProduct() {
        compositeDisposable.add(
            productApi.retrofitService.getProduct().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    homeProductLiveData.value = it
                }, {
                    Log.d("--->", it.message.orEmpty())
                })
        )
    }

    private var dataBaseInstance: ProductDetailDatabase? = null

    var personsList = MutableLiveData<List<HomeProductLayout>>()
    fun insertItem(product: HomeProductLayout) {
        viewModelScope.launch {
            productDao.insertProductData(product)
        }
    }

    fun getUpdateProductEntry(
        Title: String,
        Description: String,
        Image: String,
        Price: Double,
        Like: Boolean
    ): HomeProductLayout {
        return HomeProductLayout(
            title = Title,
            description = Description,
            image = Image,
            price = Price,
            like = Like
        )
    }

    fun updateProductItem(product: HomeProductLayout) {
        val updatedProduct = with(product) {
            getUpdateProductEntry(title, description, image, price, like)
        }
        updateProductRx(updatedProduct)
    }

    private fun updateProduct(product: HomeProductLayout) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                productDao.updateProductData(product)
            }
        }
    }

    private fun updateProductRx(product: HomeProductLayout) {
        compositeDisposable.add(
            productDao.updateProductDataRx(product)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d("Success", "Hello")
                }
        )
    }
}

class HomeViewModelFactory(private val productDao: ProductDataDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(productDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

//    fun setInstanceOfDb(dataBaseInstance: ProductDetailDatabase) {
//        this.dataBaseInstance = dataBaseInstance
//    }
//    private fun saveDataIntoDb(data: HomeProductLayout){
//        dataBaseInstance?.productDataDao()?.insertProductData(data)
//            ?.subscribeOn(Schedulers.io())
//            ?.observeOn(AndroidSchedulers.mainThread())
//            ?.subscribe ({
//            },{
//
//            })?.let {
//                compositeDisposable.add(it)
//            }
//    }
//    fun getProductData(){
//
//        dataBaseInstance?.productDataDao()?.getAllProduct()
//            ?.subscribeOn(Schedulers.io())
//            ?.observeOn(AndroidSchedulers.mainThread())
//            ?.subscribe ({
//                if(!it.isNullOrEmpty()){
//                    personsList.postValue(it)
//                }else{
//                    personsList.postValue(listOf())
//                }
//                it?.forEach {
//                    Log.v("Product Name",it.title)
//                }
//            },{
//            })?.let {
//                compositeDisposable.add(it)
//            }
//    }
//
//    override fun onCleared() {
//        compositeDisposable.dispose()
//        compositeDisposable.clear()
//        super.onCleared()
//    }
//
//    fun deleteProduct(product: Product) {
//        dataBaseInstance?.productDataDao()?.deleteProductData(product)
//            ?.subscribeOn(Schedulers.io())
//            ?.observeOn(AndroidSchedulers.mainThread())
//            ?.subscribe ({
//                //Refresh Page data
//                getProductData()
//            },{
//
//            })?.let {
//                compositeDisposable.add(it)
//            }
//    }