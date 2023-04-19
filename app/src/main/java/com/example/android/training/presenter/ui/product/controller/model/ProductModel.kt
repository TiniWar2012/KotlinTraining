package com.example.android.training.presenter.ui.product.controller.model

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.text.SpannableStringBuilder
import androidx.core.text.strikeThrough
import androidx.core.view.isVisible
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.bumptech.glide.Glide
import com.example.android.training.R
import com.example.android.training.base.EpoxyViewBindingModelWithHolder
import com.example.android.training.databinding.ItemProductV2Binding
import com.example.android.training.presenter.ui.product.model.ProductLayout

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.item_product_v2)
abstract class ProductModel : EpoxyViewBindingModelWithHolder<ItemProductV2Binding>() {

    @EpoxyAttribute
    var product: ProductLayout? = null

    @EpoxyAttribute
    lateinit var itemClick: ClickProduct

    @SuppressLint("SetTextI18n")
    override fun ItemProductV2Binding.bind(context: Context) {
        product?.let { product ->
            shimmerProduct.hideShimmer()
            shimmerProduct.stopShimmer()
            title.text = product.title.uppercase()
            description.text = product.description.uppercase()
            price.text = "฿ ${product.price}"
            Glide.with(imageView).load(product.thumbnail).placeholder(R.drawable.img_loading_icon)
                .centerCrop().into(imageView)
            discountPercent.text = "${product.discountPercentage} %"
            if (product.discountPercentage >= 30) {
                discountPercent.setBackgroundColor(Color.parseColor("#A6142E"))
            } else {
                discountPercent.setBackgroundColor(Color.parseColor("#272727"))
            }
            if (product.discountPercentage > 0) {
                priceold.isVisible
                priceold.text = SpannableStringBuilder().strikeThrough {
                    append("฿ ${product.price - (product.price * (product.discountPercentage / 100))}")
                }
            } else {
                priceold.isVisible = false
            }
            ratingBar.rating = product.rating.toFloat()
            layoutRoot.setOnClickListener {
                itemClick.onClickItem(product)
            }
        } ?: kotlin.run {
            shimmerProduct.showShimmer(true)
        }

    }
}

interface ClickProduct {
    //    fun onClickItem(homeProductLayoutNew: HomeProductLayoutNew)
    fun onClickItem(productLayout: ProductLayout)
}