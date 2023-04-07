package com.example.android.training.presenter.ui.product.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.training.R
import com.example.android.training.presenter.ui.product.model.ProductLayout

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    //    private val productList: MutableList<HomeProductLayoutNew> = mutableListOf()
    private val productList: MutableList<ProductLayout> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    //    fun setData(listProduct: List<HomeProductLayoutNew>) {
    fun setData(listProduct: List<ProductLayout>) {
        productList.addAll(listProduct)
        notifyDataSetChanged()
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(product: ProductLayout) {

            val title = view.findViewById<TextView>(R.id.title)
            val desciption = view.findViewById<TextView>(R.id.description)
            val price = view.findViewById<TextView>(R.id.price)
            val imageView = view.findViewById<ImageView>(R.id.imageView)
            val discount = view.findViewById<TextView>(R.id.discount_percent)
            val rate = view.findViewById<RatingBar>(R.id.ratingBar)

            title.text = product.title.uppercase()
            desciption.text = product.description.uppercase()
            price.text = "฿ ${product.price}"
            Glide.with(view.context).load(product.images[0])
                .placeholder(R.drawable.img_loading_icon).centerCrop().into(imageView)
            discount.text = "${product.discountPercentage} %"
            if (product.discountPercentage >= 30) {
                discount.setBackgroundColor(Color.parseColor("#A6142E"))
            } else {
                discount.setBackgroundColor(Color.parseColor("#272727"))
            }
            rate.rating = product.rating.toFloat()
//            view.setOnClickListener {
//                itemClick.onClickItem(product)
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_product_v2, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(
            productList[position],
        )
    }
}