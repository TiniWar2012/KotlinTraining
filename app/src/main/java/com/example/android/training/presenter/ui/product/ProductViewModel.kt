package com.example.android.training.presenter.ui.product

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.training.domain.GetNewProductListUseCase
import com.example.android.training.presenter.ui.home.presenter.dao.ProductDataDao
import com.example.android.training.presenter.ui.product.model.ProductLayout
import com.example.android.training.presenter.ui.product.model.ProductViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getNewProductListUseCase: GetNewProductListUseCase,
    private val productDao: ProductDataDao,
) : ViewModel() {
    private val productLiveData by lazy { MutableLiveData<ProductViewState>() }
    private val compositeDisposable = CompositeDisposable()
    val productViewStateLiveData: MediatorLiveData<ProductViewState> by lazy {
        MediatorLiveData<ProductViewState>().apply {
            addSource(productLiveData) { source ->
                productViewStateLiveData.value = source
            }
        }
    }

    init {
        getObserverHomeProduct()
    }

    private fun getObserverHomeProduct() {
        compositeDisposable.add(
            getNewProductListUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    productLiveData.value = ProductViewState.Loading
                }
                .subscribe({
                    productLiveData.value = ProductViewState.Success(it)
                }, {
                    Log.d("--->", it.message.orEmpty())
                    productLiveData.value = ProductViewState.Success(null)
                })
        )
    }

    fun getDetailProduct(product: ProductLayout) {
        compositeDisposable.add(
            productDao.getProduct(product.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    //onSuccess
                }, {
                    //onError
                })
        )
    }
}