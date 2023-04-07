package com.example.android.training.presenter.ui.home

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.training.domain.GetBannerUseCase
import com.example.android.training.domain.GetProductListUseCase
import com.example.android.training.presenter.ui.home.model.HomeBannerLayout
import com.example.android.training.presenter.ui.home.model.HomeFilterLayout
import com.example.android.training.presenter.ui.home.model.HomeProductLayout
import com.example.android.training.presenter.ui.home.model.HomeViewState
import com.example.android.training.presenter.ui.home.presenter.dao.ProductDataDao
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productDao: ProductDataDao,
    private val getBannerUseCase: GetBannerUseCase,
    private val getProductListUseCase: GetProductListUseCase
) : ViewModel() {
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
            getBannerUseCase.execute()
                .subscribeOn(Schedulers.io())
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
            getProductListUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    homeProductLiveData.value = it
                }, {
                    Log.d("--->", it.message.orEmpty())
                })
        )
    }

    fun getInsertProduct(
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

    fun likeProductItem(product: HomeProductLayout) {
        compositeDisposable.add(
            productDao.getAllProduct(product.title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isEmpty()) {
                        val inserted = with(product) {
                            getInsertProduct(title, description, image, price, like)
                        }
                        insertProductRx(inserted)
                    } else {
                        deleteProduct(product)
                    }
                }, {
                    Log.d("--->", it.message.orEmpty())
                })
        )
    }

    private fun insertProductRx(product: HomeProductLayout) {
        compositeDisposable.add(
            productDao.likeProductData(product)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d("--->", "Insert")
                }
        )
    }

    private fun deleteProduct(product: HomeProductLayout) {
        compositeDisposable.add(
            productDao.unlikeProduct(product.title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d("--->", "Delete")
                }
        )
    }

    fun deleteAllData() {
        compositeDisposable.add(
            productDao.deleteProduct()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d("--->", "Delete All Data")

                }
        )
    }
    fun getDetailProduct(product: HomeProductLayout ){
        compositeDisposable.add(
            productDao.getAllProduct(product.title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                           //onSuccess
                },{
                    //onError
                })
        )
    }
}

//class HomeViewModelFactory(private val productDao: ProductDataDao) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return HomeViewModel(productDao) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}

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