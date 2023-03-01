package com.example.android.training.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.training.R
import com.example.android.training.home.model.HomeBannerLayout

class api_news_adapter(private val newsList: List<HomeBannerLayout>) : RecyclerView.Adapter<api_news_adapter.MyViewHolder>() {

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(bm: HomeBannerLayout) {
            val imageView = view.findViewById<ImageView>(R.id.img_newsBanner)
            val title = view.findViewById<TextView>(R.id.titleNews)

            title.text = bm.description
            Glide.with(view.context).load(bm.getImageUrl()).placeholder(R.drawable.img_loading_icon)
                .centerCrop().into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_news, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}