package com.example.android.training.presenter.ui.product.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.training.R
import com.example.android.training.presenter.ui.product.model.ProductLayout

class PhotoProductAdapter : RecyclerView.Adapter<PhotoProductAdapter.MyViewHolder>() {
    private val photoList: MutableList<ProductLayout> = mutableListOf()

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val imageView = view.findViewById<ImageView>(R.id.img_banner)
        fun bind(url: String) {
            Glide.with(view.context).load(url).placeholder(R.drawable.img_loading_icon).centerCrop()
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.items_banner_image, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(photoList[position].toString())
    }
}