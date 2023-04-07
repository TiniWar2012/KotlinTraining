package com.example.android.training.presenter.ui.detail_product

import android.content.Context
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.example.android.training.R
import com.example.android.training.base.EpoxyViewBindingModelWithHolder
import com.example.android.training.databinding.FragmentDetailProductBinding
import com.example.android.training.presenter.ui.detail_product.adapter.DetailProductAdapter
import com.example.android.training.presenter.ui.home.model.HomeProductLayout

@EpoxyModelClass(layout = R.layout.fragment_detail_product)
abstract class DetailProductModel :
    EpoxyViewBindingModelWithHolder<FragmentDetailProductBinding>() {
    @EpoxyAttribute
    lateinit var productItem: List<HomeProductLayout>

    override fun FragmentDetailProductBinding.bind(context: Context) {
        val detailProductAdapter = DetailProductAdapter()
        if (::productItem.isInitialized) {
            detailProductAdapter.setData(productItem)
        }
        content.apply {

        }
    }

}