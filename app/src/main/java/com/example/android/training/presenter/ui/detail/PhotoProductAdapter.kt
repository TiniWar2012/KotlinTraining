package com.example.android.training.presenter.ui.detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.training.R

class PhotoProductAdapter : RecyclerView.Adapter<PhotoProductAdapter.MyViewHolder>() {
    private val photoList: MutableList<String> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listPhoto: List<String>) {
        photoList.addAll(listPhoto)
        notifyDataSetChanged()
    }
    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val imageView = view.findViewById<ImageView>(R.id.img_product)
        fun bind(url: String) {
            Glide.with(view.context).load(url).placeholder(R.drawable.img_loading_icon).centerCrop()
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_product_photo, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(photoList[position])
    }
}