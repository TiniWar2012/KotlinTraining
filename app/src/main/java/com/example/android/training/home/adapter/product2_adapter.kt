package com.example.android.training.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.training.R
import com.example.android.training.home.model.Product

class product2_adapter(private val data: List<Product>): RecyclerView.Adapter<product2_adapter.MyViewHolder>() {
    class MyViewHolder(val view: View):RecyclerView.ViewHolder(view) {
        fun bind(product: Product){
            val title = view.findViewById<TextView>(R.id.Title)
            val desciption = view.findViewById<TextView>(R.id.Description)
            val price = view.findViewById<TextView>(R.id.Price)
            val imageView = view.findViewById<ImageView>(R.id.imageView)

            title.text = product.title
            desciption.text = product.description.uppercase()
            price.text ="฿"+ product.price.toString()
            Glide.with(view.context).load(product.thumbnail).placeholder(R.drawable.img_loading_icon).centerCrop().into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size

    }

}