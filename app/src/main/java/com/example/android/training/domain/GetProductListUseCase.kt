package com.example.android.training.domain

import com.example.android.training.data.repository.AppRepository
import com.example.android.training.presenter.ui.home.model.HomeProductLayout
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(
    private val appRepository: AppRepository
) {
    fun execute(): Single<List<HomeProductLayout>> {
        return appRepository.getProductList()
    }
}