package com.example.android.training.domain

import com.example.android.training.data.repository.AppRepository
import com.example.android.training.presenter.ui.home.model.HomeBannerLayout
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetBannerUseCase @Inject constructor(
    private val appRepository: AppRepository
) {
    fun execute(): Single<List<HomeBannerLayout>> {
        return appRepository.getBanner()
    }
}