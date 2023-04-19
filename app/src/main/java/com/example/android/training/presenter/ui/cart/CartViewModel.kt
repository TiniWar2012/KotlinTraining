package com.example.android.training.presenter.ui.cart

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.training.domain.GetNewProductListUseCase
import com.example.android.training.presenter.ui.home.presenter.dao.ProductDataDao
import com.example.android.training.presenter.ui.product.model.ProductLayout
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getNewProductListUseCase: GetNewProductListUseCase,
    private val productDao: ProductDataDao,
) : ViewModel() {
    private val productLiveData by lazy { MutableLiveData<List<ProductLayout>>() }
    private val compositeDisposable = CompositeDisposable()
    val cartViewStateLiveData: MediatorLiveData<CartViewState> by lazy {
        MediatorLiveData<CartViewState>().apply {
            addSource(productLiveData) { source ->
                cartViewStateLiveData.value = cartViewStateLiveData.value?.copy(
                    cartDataModel = source
                ) ?: CartViewState(
                    cartDataModel = source
                )
            }
        }
    }

    init {
        getObserverCart()
    }
    private fun getObserverCart() {
        compositeDisposable.add(
            getNewProductListUseCase.execute().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    productLiveData.value = it
                }, {
                    Log.d("--->", it.message.orEmpty())
                })
        )
    }

    fun getDetailCart(product: ProductLayout) {
        compositeDisposable.add(
            productDao.getProduct(product.id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    //onSuccess
                }, {
                    //onError
                })
        )
    }
}

data class CartViewState(
    var cartDataModel: List<ProductLayout>? = null,
)
