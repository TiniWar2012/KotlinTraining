//package com.example.android.training.home.controller.model
//
//import android.content.Context
//import com.airbnb.epoxy.EpoxyAttribute
//import com.airbnb.epoxy.EpoxyModelClass
//import com.example.android.training.R
//import com.example.android.training.base.EpoxyViewBindingModelWithHolder
//import com.example.android.training.databinding.LayoutHeaderBannerBinding
//import com.example.android.training.databinding.LayoutProductRecyclerviewBinding
//import com.example.android.training.home.adapter.ProductAdapter
//import com.example.android.training.home.model.HomeProductLayout
//
//@EpoxyModelClass(layout = R.layout.layout_product_recyclerview)
//abstract class NewArrivalProductModel : EpoxyViewBindingModelWithHolder<LayoutProductRecyclerviewBinding>() {
//    @EpoxyAttribute
//    lateinit var listProduct : List<HomeProductLayout>
//    override fun LayoutProductRecyclerviewBinding.bind(context: Context) {
//        val productAdapter = ProductAdapter()
//        if (::listProduct.isInitialized){
//            productAdapter.setData(listProduct)
//        }
//        recyclerView.apply {
//            adapter = productAdapter
//            productListType.text = "NEW ARRIVALS"
//        }
//    }
//}