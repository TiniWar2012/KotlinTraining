package com.example.android.training.presenter.ui.detail_product.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.training.R
import com.example.android.training.presenter.ui.home.model.HomeProductLayout

class DetailProductAdapter() : RecyclerView.Adapter<DetailProductAdapter.ViewHolder>() {
    private val productItem: MutableList<HomeProductLayout> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(detailProduct: List<HomeProductLayout>) {
        productItem.addAll(detailProduct)
        notifyDataSetChanged()
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("ResourceAsColor")
        fun bind(product: HomeProductLayout) {
            val imageView = view.findViewById<ImageView>(R.id.product_photo)
            Glide.with(view.context).load(product.image).placeholder(R.drawable.img_loading_icon)
                .centerCrop().into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_detail_product, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productItem.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productItem[position])
    }


}


