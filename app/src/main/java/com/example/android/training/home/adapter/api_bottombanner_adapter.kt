package com.example.android.training.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.training.R
import com.example.android.training.home.model.HomeBannerLayout

class api_bottombanner_adapter(private val apiBannerList: List<HomeBannerLayout>) : RecyclerView.Adapter<api_bottombanner_adapter.MyViewHolder>() {

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imageView = view.findViewById<ImageView>(R.id.img_bottomBanner)
        fun bind(bm: HomeBannerLayout) {
            Glide.with(view.context).load(bm.getImageUrl()).placeholder(R.drawable.img_loading_icon)
                .centerCrop().into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_bottombanner, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(apiBannerList[position])
    }

    override fun getItemCount(): Int {
        return apiBannerList.size
    }
}