package com.example.android.training.domain

import com.example.android.training.data.repository.AppRepository
import com.example.android.training.presenter.ui.product.model.ProductLayout
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetNewProductListUseCase @Inject constructor(
    private val appRepository: AppRepository
) {
    fun execute(): Single<List<ProductLayout>> {
        return appRepository.getNewProductList()
    }
}