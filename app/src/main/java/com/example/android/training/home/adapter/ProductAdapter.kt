package com.example.android.training.home.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.training.R
import com.example.android.training.home.model.HomeProductLayout

class ProductAdapter(private val onClick: HandleClickListener) : RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {
    private val productList: MutableList<HomeProductLayout> = mutableListOf()
    @SuppressLint("NotifyDataSetChanged")
    fun setData(listProduct: List<HomeProductLayout>){
        productList.addAll(listProduct)
        notifyDataSetChanged()
    }
    class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){
        @SuppressLint("ResourceAsColor")
        fun bind(product: HomeProductLayout, onClick: HandleClickListener){
            val title = view.findViewById<TextView>(R.id.Title)
            val desciption = view.findViewById<TextView>(R.id.Description)
            val price = view.findViewById<TextView>(R.id.Price)
            val imageView = view.findViewById<ImageView>(R.id.imageView)
            val like = view.findViewById<ImageButton>(R.id.button_favorite)


            title.text = product.title.uppercase()
            desciption.text = product.description.uppercase()
            price.text ="฿"+ product.price.toString()
            Glide.with(view.context).load(product.image).placeholder(R.drawable.img_loading_icon).centerCrop().into(imageView)

            if (product.like == true){
                like.setImageResource(R.drawable.ic_favorite)
                Log.d("--->","true")
            }else{
                like.setImageResource(R.drawable.ic_unfavorite)
                Log.d("--->","false")
            }
            like.setOnClickListener{
                if(product.like){
                    product.like = false
                    like.setImageResource(R.drawable.ic_unfavorite)
                    onClick.onClick(product)
                }else{
                    product.like = true
                    like.setImageResource(R.drawable.ic_favorite)
                    onClick.onClick(product)
                }
            }

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
        holder.bind(productList[position], onClick)
    }
}

interface HandleClickListener {
    fun onClick(homeProductLayout: HomeProductLayout)
}
