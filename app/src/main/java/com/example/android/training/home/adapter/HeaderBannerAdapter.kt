package com.example.android.training.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.training.R
import com.example.android.training.home.model.HomeBannerLayout

class HeaderBannerAdapter() : RecyclerView.Adapter<HeaderBannerAdapter.MyViewHolder>() {
    private val BannerList: MutableList<HomeBannerLayout> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listBanner : List<HomeBannerLayout>){
        BannerList.addAll(listBanner)
        notifyDataSetChanged()
    }

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val imageView = view.findViewById<ImageView>(R.id.img_banner)
        fun bind(photo: HomeBannerLayout) {
            Glide.with(view.context).load(photo.getImageUrl()).placeholder(R.drawable.img_loading_icon)
                .centerCrop().into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.items_banner_image, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(BannerList[position])
    }

    override fun getItemCount(): Int {
        return BannerList.size
    }
}