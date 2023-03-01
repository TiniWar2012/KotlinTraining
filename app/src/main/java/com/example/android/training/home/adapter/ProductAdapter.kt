package com.example.android.training.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.training.R
import com.example.android.training.home.model.HomeBannerLayout
import com.example.android.training.home.model.HomeProductLayout

class ProductAdapter() : RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {
    private val productList: MutableList<HomeProductLayout> = mutableListOf()
    @SuppressLint("NotifyDataSetChanged")
    fun setData(listProduct: List<HomeProductLayout>){
        productList.addAll(listProduct)
    }
    class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){
        fun bind(product: HomeProductLayout){
            val title = view.findViewById<TextView>(R.id.Title)
            val desciption = view.findViewById<TextView>(R.id.Description)
            val price = view.findViewById<TextView>(R.id.Price)
            val imageView = view.findViewById<ImageView>(R.id.imageView)

            title.text = product.title.uppercase()
            desciption.text = product.description.uppercase()
            price.text ="฿"+ product.price.toString()
            Glide.with(view.context).load(product.image).placeholder(R.drawable.img_loading_icon).centerCrop().into(imageView)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(productList[position])
    }
}